package iwwwdnw.spielzug.impl;

import iwwwdnw.spielzug.port.Field;
import iwwwdnw.spielzug.port.Pawn;

public class PawnImpl implements Pawn {
	
	private Field currentField;
	
	public PawnImpl(Field startField) {
		this.currentField = startField;
	}

	@Override
	public Field getCurrentField() {
		return currentField;
	}
	@Override
	public void setCurrentField(Field currentField) {
		this.currentField = currentField;
	}
	
}
