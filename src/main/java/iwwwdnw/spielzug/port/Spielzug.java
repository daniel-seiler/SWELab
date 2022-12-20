
package iwwwdnw.spielzug.port;

import Analyse.ObjectModel.Spieler;
import Analyse.ObjectModel.Spielfigur;
import Analyse.ObjectModel.Startfeld;

public interface Spielzug {



	void finishTurn();

	void moveFig(Spielfigur figur, Analyse.ObjectModel.Feld felder);

	void moveFigToStart(Startfeld startfeld);

	void confirmDiceResult();

	void throwDice(Spieler spieler);
}
