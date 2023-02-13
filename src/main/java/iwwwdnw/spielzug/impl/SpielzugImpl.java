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
	
	private PlayerImpl currentPlayer;
	private DiceResult diceResult;
	private final Board board;
	private final StateMachine stateMachine;
	private Dice dice;

	private TurnInfo turnInfo;
	private static final int MAX_THROWS = 3;
	private static final int MAGIC_NUMBER = 7;
	private Map<Pawn, List<Field>> currentMovements = new HashMap<>();
	
	public SpielzugImpl(StateMachinePort stateMachinePort) {
		this.stateMachine = stateMachinePort.stateMachine();
		this.board = new Board();
		this.dice = new Dice();
		currentPlayer = board.getPlayers().get(0);
		stateMachine.setState(S.DiceAvailable);
	}
	
	
	@Override
	public void finishTurn() {
		currentPlayer = board.getPlayers().get((currentPlayer.getStartPosition() + 1) % Board.NUM_PLAYER);
		dice = new Dice();
		currentMovements.clear();
		diceResult = null;
		turnInfo = null;
		stateMachine.setState(S.DiceAvailable);
	}

	@Override
	public void movePawn(int fieldId, int pawnId) {
		Field field = board.getField(fieldId);
		Pawn pawn = getPawn(pawnId);
				PlayerImpl duellPlayer = null;
		if (pawn.getCurrentField().get() == FieldType.HomeField || diceResult.getResult() - getDifference(field, pawn) < 0) {
			// move not possible, because move is to far, or pawn is still in homefield
			stateMachine.setState(S.SelectFigureToMove);
			turnInfo = new TurnInfo(false, board, board.getPlayers(), currentPlayer, diceResult, null);
			return;
		}
		// check for positioning of all pawns of all players
		for (PlayerImpl player : board.getPlayers()) {
			if (player.equals(currentPlayer)) {
				// check if all rules of current player positioning are correct
				if (checkPawnsOfCurrentPlayer(field, pawn)) {
					stateMachine.setState(S.SelectFigureToMove);
					turnInfo = new TurnInfo(false, board, board.getPlayers(), currentPlayer, diceResult, null);
					return;
				}
			} else {
				//check if pawn will be on other players boardField
				if (checkPawnsOfOtherPlayer(player, field)) {
					duellPlayer = player;
					for (Pawn p : player.getPawns()) {
						if (p.getCurrentField().equals(field)) {
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
		diceResult.sub(getDifference(field, pawn));
		pawn.setCurrentField(field);
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
			if(p.getCurrentField().equals(field)) {
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
			if (p.equals(pawn) && currentMovements.containsKey(p)) {
				//step onto trail of current pawn
				if(board.getFieldsInRange(pawn.getCurrentField().getFieldID(), field.getFieldID()).stream().
						filter(e -> currentMovements.get(pawn).stream().
								anyMatch(f -> f.equals(e))).collect(Collectors.toSet()).size() > 1) return true;
			} else {
				//step on to field of own pawn
				if (p.getCurrentField().equals(field)) return true;
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
		return (board != null) ? board.toString() : null;
	}

	@Override
	public String getMovementResult() {
		return (turnInfo != null) ? turnInfo.toString() : "Not yet moved";
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
