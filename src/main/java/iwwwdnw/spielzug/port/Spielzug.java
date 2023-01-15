
package iwwwdnw.spielzug.port;

public interface Spielzug {



	void finishTurn();

	TurnInfo movePawn(Field field, Pawn pawn);

	void pawnToStartField(Field startField);

	TurnInfo checkDiceResult();

	DiceResult throwDice(Player player);
}
