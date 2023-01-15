package iwwwdnw.spielzug.impl;

import iwwwdnw.spielzug.port.Spielzug;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iwwwdnw.spielzug.impl.DiceResult;
import iwwwdnw.spielzug.port.Field;
import iwwwdnw.spielzug.port.Field.FieldType;
import iwwwdnw.spielzug.impl.PawnImpl;
import iwwwdnw.spielzug.impl.PlayerImpl;
import iwwwdnw.spielzug.impl.TurnInfo;
import iwwwdnw.statemachine.port.State.S;
import iwwwdnw.statemachine.port.StateMachine;

public class SpielzugImpl implements Spielzug {
	
	private PlayerImpl currentPlayer;
	private DiceResult diceResult;
	private final Board board;
	private final StateMachine stateMachine;
	private Dice dice;
	private static final int MAX_THROWS = 3;
	private static final int MAGIC_NUMBER = 7;
	private Map<PawnImpl, List<Field>> currentMovements = new HashMap<>();
	
	public SpielzugImpl(StateMachine stateMachine, Board board) {
		this.stateMachine = stateMachine;
		this.board = board;
		this.dice = new Dice();
	}
	
	public void newTurn(PlayerImpl player) {
		currentPlayer = player;
	}
	
	
	@Override
	public void finishTurn() {
		//TODO
	}

	@Override
	public TurnInfo movePawn(Field field, PawnImpl pawn) {
		List<Field> movement = currentMovements.get(pawn);
		/*if (movement != null) {
			if (movement.stream().anyMatch(e -> e.equals(field))) {
				return new TurnInfo(false);
			}
		}*/
		// check for positioning of all pawns of all players
		for (PlayerImpl player : board.getPlayers()) {
			if (player.equals(currentPlayer)) {
				// check if all rules of current player positioning are correct
				if (!checkPawnsOfCurrentPlayer(field, pawn)) {
					return new TurnInfo(false); 
				}
			} else {
				//check if pawn will be on other players field
				if (checkPawnsOfOtherPlayer(player, field)) {
					//TODO DUELL!!
				}
			}
		}
		for (int i = Math.min(pawn.getCurrentField().getFieldID(), field.getFieldID()); i <= Math.max(field.getFieldID(), pawn.getCurrentField().getFieldID()); i++) {
			board.g
		}
		return new TurnInfo(true);
	}
	
	/*
	 * @return true on duell
	 */
	Boolean checkPawnsOfOtherPlayer(PlayerImpl player, Field field) {
		for (PawnImpl p : player.getPawns()) {
			if(p.getCurrentField().getFieldID() == field.getFieldID()) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * @return true on error
	 */
	Boolean checkPawnsOfCurrentPlayer(Field field, PawnImpl pawn) {
		for (PawnImpl p : currentPlayer.getPawns()) {
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
	
	private Boolean checkIfPawnStepsOnField(Field fieldToCheck, Field field, PawnImpl pawn) {
		for (int i = Math.min(pawn.getCurrentField().getFieldID(), field.getFieldID()); i <= Math.max(field.getFieldID(), pawn.getCurrentField().getFieldID()); i++) {
			if (i == fieldToCheck.getFieldID()) {
				return false;
			}
		}
		return false;
	}

	@Override
	public void pawnToStartField(Field startField) {
		List<PawnImpl> pawns = currentPlayer.getPawns();
		Field startfield = board.getStartField(currentPlayer);
		for (PawnImpl pawn : pawns) {
			if (pawn.getCurrentField().get() == FieldType.HomeField) {
				pawn.setCurrentField(startfield);
				stateMachine.setState(S.FinishTurn);
				return;
			}
		}
	}
	
	private void initNewTurn(PlayerImpl player) {
		currentPlayer = player;
		dice = new Dice();
		currentMovements.clear();
	}

	@Override
	public DiceResult throwDice(PlayerImpl player) {
		// if new turn -> set current player to new player
		if (player != currentPlayer) initNewTurn(player);
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
		return diceResult;
	}

	
}
