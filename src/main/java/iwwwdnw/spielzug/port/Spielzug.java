
package iwwwdnw.spielzug.port;

import Analyse.ObjectModel.Spieler;
import Analyse.ObjectModel.Spielfeld;
import Analyse.ObjectModel.Spielfigur;
import Analyse.ObjectModel.Startfeld;

public interface Spielzug {



	void spielzugBeenden();

	Spielfeld figurenBewegen(Spielfigur figur, Analyse.ObjectModel.Feld felder);

	void figurAufStartefeld(Startfeld startfeld);

	Integer wuerfelergebnisPruefen();

	void wuerfeln(Spieler spieler);
}
