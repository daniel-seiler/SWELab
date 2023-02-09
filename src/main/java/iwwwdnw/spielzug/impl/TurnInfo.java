package iwwwdnw.spielzug.impl;

import java.util.List;

import iwwwdnw.spielzug.port.Field.FieldType;

public class TurnInfo {
	
	private Boolean success;
	private Board board;
	private List<PlayerImpl> players;
	private PlayerImpl currentPlayer;
	private DiceResult diceResult;
	private PlayerImpl duellPlayer;

	TurnInfo(PlayerImpl currentPlayer) {
		this.currentPlayer = currentPlayer;
	}


	TurnInfo(Boolean success, Board board, List<PlayerImpl> players, PlayerImpl currentPlayer, DiceResult diceResult, PlayerImpl duellPlayer) {
		this.success = success;
		this.board = board;
		this.players = players;
		this.currentPlayer = currentPlayer;
		this.diceResult = diceResult;
		this.duellPlayer = duellPlayer;
	}

	//uebrige stchritte
	//duell
	//positionen

    @Override
    public String toString() {
		String resultString;
		if (success) {
			resultString = "Finished turn with success\n";
			if (duellPlayer != null) {
				resultString += currentPlayer.getColour().name() + " won a duel against " + duellPlayer.getColour().name() + "\n";
			}
		} else {
			resultString = "ERROR: movement not allowed\n";
		}
		return getPostitions(resultString);
	}

    private String getPostitions(String resultString) {
    	resultString +=  currentPlayer.getColour().name() + " is currently playing\n";
    	if (diceResult != null) {
    		resultString += "Number of possible movements: " + diceResult.getResult() + "\n";
    	}
		StringBuilder resultStringBuilder = new StringBuilder(resultString);
		for (PlayerImpl player : players) {
			resultStringBuilder.append(player.getColour().name()).append(": ");
			for (int i = 0; i < player.getPawns().size(); i++) {
				resultStringBuilder.append("(pawn_").append(i).append(": ");
				if (player.getPawns().get(i).getCurrentField().get() == FieldType.HomeField) {
					resultStringBuilder.append(" homefield), ");
				} else {
					resultStringBuilder.append("field ").append(player.getPawns().get(i).getCurrentField().getFieldID()).append("), ");
				}
			}
			resultStringBuilder.append("\n");
		}
		resultString = resultStringBuilder.toString();
		return resultString;
    }

    public String getBoard() {
    	return board.toString();
    }

}
