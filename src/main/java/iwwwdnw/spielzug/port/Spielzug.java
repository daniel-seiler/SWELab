
package iwwwdnw.spielzug.port;

import iwwwdnw.spielzug.impl.DiceResult;
import iwwwdnw.spielzug.impl.PawnImpl;
import iwwwdnw.spielzug.impl.PlayerImpl;
import iwwwdnw.spielzug.impl.TurnInfo;

public interface Spielzug {


	void finishTurn();

	TurnInfo movePawn(Field field, PawnImpl pawn);

	void pawnToStartField(Field startField);

	DiceResult throwDice();
	
	
}
