package iwwwdnw.spielzug.impl;

import iwwwdnw.spielzug.port.Pawn;
import iwwwdnw.spielzug.port.Spielzug;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import iwwwdnw.spielzug.port.Field;
import iwwwdnw.spielzug.port.Field.FieldType;
import iwwwdnw.spielzug.port.SpielzugInfo;
import iwwwdnw.statemachine.port.State.S;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.statemachine.port.StateMachinePort;

public class SpielzugImpl implements Spielzug, SpielzugInfo {
	/**
	 * The player who is currently playing
	 * switches after finishedTurn is called
	 */
	private PlayerImpl currentPlayer;
	/**
	 * holds the value of the current dice role/ remaining moves
	 */
	private DiceResult diceResult;
	/**
	 * Saves the structure of the playing field
	 */
	private final Board board;
	/**
	 * needed to set new states that notifies the controller and view
	 */
	private final StateMachine stateMachine;

	/**
	 * Presents the rolling dice function and counts the number of throws
	 */
	private Dice dice;
	/**
	 * Holds information on the outcome of the current move
	 */
	private TurnInfo turnInfo;
	/**
	 * Max throws to get a seven
	 */
	private static final int MAX_THROWS = 3;
	/**
	 * Number for moving a new pawn to start field
	 */
	private static final int MAGIC_NUMBER = 7;
	/**
	 * Hold information on the movements in the current turn,
	 * to prevent pawns walking on their own trail
	 */
	private final Map<Pawn, List<Field>> currentMovements = new HashMap<>();
	
	public SpielzugImpl(StateMachinePort stateMachinePort) {
		this.stateMachine = stateMachinePort.stateMachine();
		this.board = new Board();
		this.dice = new Dice();
		currentPlayer = board.getPlayers().get(0);
		stateMachine.setState(S.DiceAvailable);
	}

	/**
	 * Calculates and sets the new player
	 * Clears all information of the previous player
	 * Sets state to DiceAvailable
	 */
	@Override
	public void finishTurn() {
		currentPlayer = board.getPlayers().get((currentPlayer.getStartPosition() + 1) % Board.NUM_PLAYER);
		dice = new Dice();
		currentMovements.clear();
		diceResult = null;
		turnInfo = null;
		stateMachine.setState(S.DiceAvailable);
	}

	/**
	 * Moves a pawn with @pawnId to a field @fieldId if possible
	 * Saves outcome in turnInfo and sets state according to new information
	 * @param fieldId id of field where pawn is moved to
	 * @param pawnId id of pawn
	 */
	@Override
	public void movePawn(int fieldId, int pawnId) {
		//init
		PlayerImpl duellPlayer = null;
		boolean success = pawnId < board.getPlayers().size() && fieldId < Board.FIELDS_TOTAL;

		if (success) {
			// current field and pawn
			Field field = board.getField(fieldId);
			Pawn pawn = getPawn(pawnId);

			// check for move to far or pawn in home field
			success = checkIfMovePossible(pawn, field);

			// check for positioning of all pawns of all players
			for (int i = 0; i < board.getPlayers().size() && success ; i++) {
				if (board.getPlayers().get(i).equals(currentPlayer)) {
					// check if all rules of current player positioning are correct
					success = checkPawnsOfCurrentPlayer(field, pawn);
				} else {
					//check if pawn will be on other players boardField
					PlayerImpl duel = checkPositioningOfOtherPlayers(board.getPlayers().get(i), field);
					if (duellPlayer == null && duel != null) duellPlayer = duel;
				}
			}
			if (success) saveMove(pawn, field);
		}
		turnInfo = new TurnInfo(success, duellPlayer);
	}

	/**
	 * moves are possible and are here saved in @currentMovements
	 * diceResult is updated to match the number of remaining moves
	 * sets the states to finishTurn if remaining moves are 0
	 * @param pawn pawn to be moved
	 * @param field field to move to
	 */
	void saveMove(Pawn pawn, Field field) {
		List<Field> movements = board.getFieldsInRange(pawn.getCurrentField().getFieldID(), field.getFieldID());
		if (currentMovements.containsKey(pawn)) {
			currentMovements.get(pawn).addAll(movements);
		} else {
			currentMovements.put(pawn, movements);
		}
		diceResult.sub(getDifference(field, pawn));
		pawn.setCurrentField(field);
		if (diceResult.isZero()) {
			// User turn finished
			stateMachine.setState(S.FinishTurn);
		} else {
			// User can still move pawns
			stateMachine.setState(S.SelectFigureToMove);
		}
	}

	/**
	 * Check if move is possible by checking if number of possible movements is in range
	 * @param pawn
	 * @param field
	 * @return
	 */
	boolean checkIfMovePossible(Pawn pawn, Field field) {
		if (pawn.getCurrentField().get() == FieldType.HomeField || diceResult.getResult() - getDifference(field, pawn) < 0) {
			// move not possible, because move is to far, or pawn is still in homefield
			stateMachine.setState(S.SelectFigureToMove);
			return false;
		}
		return true;
	}

	/**
	 * Checks if another @player has a pawn on @field
	 * @param player other player to check
	 * @param field field where pawn of currentPlayer is moved to
	 * @return the duelPlayer if one exists, otherwise null
	 */
	PlayerImpl checkPositioningOfOtherPlayers(PlayerImpl player, Field field) {
		PlayerImpl duellPlayer = null;
		if (checkPawnsOfOtherPlayer(player, field)) {
			duellPlayer = player;
			for (Pawn p : player.getPawns()) {
				if (p.getCurrentField().equals(field)) {
					// ------------------------ DDDUELLL --------------------------
					p.setCurrentField(new HomeField());
				}
			}
		}
		return  duellPlayer;
	}

	/**
	 * Calculates the number of moves to walk between the current field of @pawn and @field
	 * @param field to move to
	 * @param pawn current pawn
	 * @return number of needed moves
	 */
	int getDifference(Field field, Pawn pawn) {
		int max = Math.max(pawn.getCurrentField().getFieldID(), field.getFieldID());
    	int min = Math.min(pawn.getCurrentField().getFieldID(), field.getFieldID());
    	return Math.min(max - min, min + Board.FIELDS_TOTAL - max);
	}

	/**
	 * Checks if a duel is happening with @player
	 * @param player other player to check
	 * @param field field where duel could happen
	 * @return true on duel, false on no duel
	 */
	Boolean checkPawnsOfOtherPlayer(PlayerImpl player, Field field) {
		for (Pawn p : player.getPawns()) {
			if(p.getCurrentField().equals(field)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if move of current Player is allows by checking saved movements and
	 * placing of his own pawns
	 * @param field to move to
	 * @param pawn pawn that is moved
	 * @return true on possible move, false on error
	 */
	Boolean checkPawnsOfCurrentPlayer(Field field, Pawn pawn) {
		for (Pawn p : currentPlayer.getPawns()) {
			if (p.equals(pawn) && currentMovements.containsKey(p)) {
				//step onto trail of current pawn
				if(board.getFieldsInRange(pawn.getCurrentField().getFieldID(), field.getFieldID()).stream().
						filter(e -> currentMovements.get(pawn).stream().
								anyMatch(f -> f.equals(e))).collect(Collectors.toSet()).size() > 1) {
					stateMachine.setState(S.SelectFigureToMove);
					return false;
				}
			} else {
				//step on to field of own pawn
				if (p.getCurrentField().equals(field)) {
					stateMachine.setState(S.SelectFigureToMove);
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Moves a pawn that is currently in a HomeField to the StartField
	 */
	@Override
	public void pawnToStartField() {
		List<Pawn> pawns = currentPlayer.getPawns();
		Field startfield = board.getStartField(currentPlayer);
		for (Pawn pawn : pawns) {
			if (pawn.getCurrentField().get() == FieldType.HomeField) {
				pawn.setCurrentField(startfield);
				stateMachine.setState(S.FinishTurn);
				return;
			}
		}
	}

	/**
	 * Rolls a new random dice number and sets state according to result
	 */
	@Override
	public void throwDice() {
		// get random dice number
		diceResult = dice.rollTwo();
		// check result for turn options
		// check for 7
		if (diceResult.getResult() == MAGIC_NUMBER) {
			// check for all occupied start
			if (currentPlayer.getPawns().stream().anyMatch(p -> p.getCurrentField().get() == FieldType.StartField)) {
				stateMachine.setState(S.SelectFigureToMove);
			} else {
				stateMachine.setState(S.SelectFigureToStartfield);
			}
		} else {
			// check for all pawns in homeField
			if (currentPlayer.getPawns().stream().allMatch(p -> p.getCurrentField().get() == FieldType.HomeField)) {
				// check for number of throws
				if (dice.getNumThrows() == MAX_THROWS) {
					stateMachine.setState(S.FinishTurn);
				} else {
					stateMachine.setState(S.DiceAvailable);
				}
			} else {
				stateMachine.setState(S.SelectFigureToMove);
			}
		}
	}
    
    public Pawn getPawn(int id) {
        return currentPlayer.getPawns().get(id);
    }

	@Override
	public String getBoard() {
		return (board != null) ? board.toString() : null;
	}

	@Override
	public String getMovementResult() {
		return (turnInfo != null) ? turnInfo.toString(diceResult, currentPlayer, board) : "Not yet moved";
	}

	@Override
	public String getDiceResult() {
		return diceResult != null ? diceResult.toString() : "No result available";
	}

	@Override
	public String currentPlayer() {
		return  currentPlayer.getName();
	}
}
