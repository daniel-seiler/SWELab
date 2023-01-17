package iwwwdnw.spielzug.impl;

import java.util.List;

import iwwwdnw.spielzug.port.Field.FieldType;

public class TurnInfo {
	
	private final Boolean success;
	private final Board board;
	private final List<PlayerImpl> players;
	private final PlayerImpl currentPlayer;
	private final DiceResult diceResult;
	private final PlayerImpl duellPlayer;
	
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
        //TODO
		if (success) {
			String resultString = "Spielzug durchgeführt\n";
			if (duellPlayer != null) {
				resultString += currentPlayer.getColour().name() + " hat ein DUELL gewonnen gegen" + duellPlayer.getColour().name() + "\n";
			}
			return getPostitions(resultString);
		} else {
			String resultString = "Spielzug fehlgeschlagen\n";
			return getPostitions(resultString);
		}
    }
    
    private String getPostitions(String resultString) {
    	resultString +=  currentPlayer.getColour().name() + " ist am Zug\n";
    	if (diceResult != null) {
    		resultString += "Noch uebrige Augenzahlen: " + diceResult.getResult() + "\n";
    	}
    	for (PlayerImpl player : players) {
			resultString += player.getColour().name() + ": ";
			for (int i = 0; i < player.getPawns().size(); i++) {
				resultString += "(Spieler " + i + ": ";
				if (player.getPawns().get(i).getCurrentField().get() == FieldType.HomeField) {
					resultString += " Heimatfeld), ";
				} else {
					resultString += "Feld " + player.getPawns().get(i).getCurrentField().getFieldID() + "), ";
				}
			}
			resultString += "\n";
		}
    	return resultString;
    }
    
    public String getBoard() {
    	return board.toString();
    }
  
}
