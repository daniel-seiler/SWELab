package iwwwdnw.spielzug.impl;

import iwwwdnw.spielzug.port.Pawn;
import iwwwdnw.spielzug.port.Spielzug;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import iwwwdnw.spielzug.port.Field;
import iwwwdnw.spielzug.port.Field.FieldType;
import iwwwdnw.spielzug.port.SpielzugInfo;
import iwwwdnw.statemachine.port.State.S;
import iwwwdnw.statemachine.port.StateMachine;

public class SpielzugImpl implements Spielzug, SpielzugInfo {
	
	private PlayerImpl currentPlayer;
	private DiceResult diceResult;
	private final Board board;
	private final StateMachine stateMachine;
	private Dice dice;

	private TurnInfo turnInfo;
	private static final int MAX_THROWS = 3;
	private static final int MAGIC_NUMBER = 7;
	private Map<Pawn, List<Field>> currentMovements = new HashMap<>();
	
	public SpielzugImpl(StateMachine stateMachine, Board board) {
		this.stateMachine = stateMachine;
		this.board = board;
		this.dice = new Dice();
		currentPlayer = board.getPlayers().get(0);
		stateMachine.setState(S.DiceAvailable);
	}
	
	
	@Override
	public void finishTurn() {
		currentPlayer = board.getPlayers().get((currentPlayer.getStartPosition() + 1) % Board.NUM_PLAYER);
		dice = new Dice();
		currentMovements.clear();
		stateMachine.setState(S.DiceAvailable);
        //TODO return next player
	}

	@Override
	public void movePawn(Field field, Pawn pawn) {
		PlayerImpl duellPlayer = null;
		if (diceResult.getResult() - getDifference(field, pawn) < 0) {
			// move not possible, because move is to far
			stateMachine.setState(S.SelectFigureToMove);
			turnInfo = new TurnInfo(false, board, board.getPlayers(), currentPlayer, diceResult, null);
		}
		// check for positioning of all pawns of all players
		for (PlayerImpl player : board.getPlayers()) {
			if (player.equals(currentPlayer)) {
				// check if all rules of current player positioning are correct
				if (!checkPawnsOfCurrentPlayer(field,pawn)) {
					stateMachine.setState(S.SelectFigureToMove);
					turnInfo = new TurnInfo(false, board, board.getPlayers(), currentPlayer, diceResult, null);
				}
			} else {
				//check if pawn will be on other players field
				if (checkPawnsOfOtherPlayer(player, field)) {
					duellPlayer = player;
					for (Pawn p : player.getPawns()) {
						if (pawn.getCurrentField().equals(field)) {
							p.setCurrentField(new HomeField());
						}
					}
				}
			}
		}
		// save this move
		if ( currentMovements.containsKey(pawn)) {
			currentMovements.get(pawn).addAll(board.getFieldsInRange(pawn.getCurrentField().getFieldID(), field.getFieldID()));
		} else {
			currentMovements.put(pawn, board.getFieldsInRange(pawn.getCurrentField().getFieldID(), field.getFieldID()));
		}
		pawn.setCurrentField(field);
		diceResult.sub(getDifference(field, pawn));
		if (diceResult.isZero()) {
			// User turn finished
			stateMachine.setState(S.FinishTurn);
		} else {
			// User can still move pawns
			stateMachine.setState(S.SelectFigureToMove);
		}
		turnInfo = new TurnInfo(true, board, board.getPlayers(), currentPlayer, diceResult, duellPlayer);
	}
	
	int getDifference(Field field, Pawn pawn) {
		int max = Math.max(pawn.getCurrentField().getFieldID(), field.getFieldID());
    	int min = Math.min(pawn.getCurrentField().getFieldID(), field.getFieldID());
    	return Math.min(max - min, min + Board.FIELDS_TOTAL - max);
	}
	
	/*
	 * @return true on duell
	 */
	Boolean checkPawnsOfOtherPlayer(PlayerImpl player, Field field) {
		for (Pawn p : player.getPawns()) {
			if(p.getCurrentField().getFieldID() == field.getFieldID()) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * @return true on error
	 */
	Boolean checkPawnsOfCurrentPlayer(Field field, Pawn pawn) {
		for (Pawn p : currentPlayer.getPawns()) {
			if (currentMovements.containsKey(p)) {
				for (Field f : currentMovements.get(p)) {
					if (checkIfPawnStepsOnField(f, field, pawn)) return true;
				}
			} else {
				if (checkIfPawnStepsOnField(p.getCurrentField(), field, pawn)) return true;
			}
		}
		return false;
	}
	
	private Boolean checkIfPawnStepsOnField(Field fieldToCheck, Field field, Pawn pawn) {
		return board.getFieldsInRange(pawn.getCurrentField().getFieldID(), field.getFieldID()).stream().anyMatch(f -> f.equals(fieldToCheck));
	}

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
        //TODO return move info
	}

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
    
    public Field getField(int id) {
        return board.getField(id);
    }
    
    public Pawn getPawn(int id) {
        return currentPlayer.getPawns().get(id);
    }

	@Override
	public String getBoard() {
		return (turnInfo != null) ? turnInfo.getBoard() : null;
	}

	@Override
	public String getMovementResult() {
		return (turnInfo != null) ? turnInfo.toString() : null;
	}

	@Override
	public String getDiceResult() {
		return diceResult != null ? diceResult.toString() : null;
	}

	@Override
	public String currentPlayer() {
		return  currentPlayer.getName();
	}
}
