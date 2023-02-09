package iwwwdnw.spielzug.port;

import iwwwdnw.spielzug.impl.PawnImpl;

public interface Spielzug {


	void finishTurn();

	void movePawn(Field field, Pawn pawn);

	void pawnToStartField();

	void throwDice();
	
	
	
	
}
