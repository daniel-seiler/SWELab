package iwwwdnw.spielzug.port;

import iwwwdnw.spielzug.impl.PawnImpl;

public interface Spielzug {


	void finishTurn();

	void movePawn(int fieldId, int pawnId);

	void pawnToStartField();

	void throwDice();
	
	
	
	
}
