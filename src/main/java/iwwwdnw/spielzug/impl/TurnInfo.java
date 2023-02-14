package iwwwdnw.spielzug.impl;

import java.util.List;

import iwwwdnw.spielzug.port.Field.FieldType;

public class TurnInfo {
	
	private final Boolean success;
	private final PlayerImpl duellPlayer;

	TurnInfo(Boolean success, PlayerImpl duellPlayer) {
		this.success = success;
		this.duellPlayer = duellPlayer;
	}

    public String toString(DiceResult diceResult, PlayerImpl currentPlayer, Board board) {
		String resultString;
		if (success) {
			resultString = "---- Finished movement with success ";
			if (duellPlayer != null) {
				resultString += "(" + currentPlayer.getColour().name() + " won a duel against " + duellPlayer.getColour().name() + ")";
			}
			resultString += "-----\n";
		} else {
			resultString = "---- ERROR: movement not allowed\n ----";
		}
		return getPostitions(resultString, diceResult, currentPlayer, board);
	}

    private String getPostitions(String resultString, DiceResult diceResult, PlayerImpl currentPlayer,  Board board) {
    	if (diceResult != null) {
    		resultString += "Number of possible movements: " + diceResult.getResult() + "\n";
    	}
		StringBuilder resultStringBuilder = new StringBuilder(resultString);
		for (PlayerImpl player : board.getPlayers()) {
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
}
