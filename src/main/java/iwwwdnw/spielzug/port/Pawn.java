package iwwwdnw.spielzug.port;

import iwwwdnw.spielzug.impl.Colour;
import iwwwdnw.spielzug.impl.PawnImpl;

import java.util.List;

public interface Pawn {

    public Field getCurrentField();

    public void setCurrentField(Field currentField);
}
