package iwwwdnw.spielzug.port;

import iwwwdnw.spielzug.impl.DiceResult;
import iwwwdnw.spielzug.impl.TurnInfo;

public interface SpielzugInfo {
    String getBoard();

    String getMovementResult();

    String getDiceResult();

    String currentPlayer();




}
