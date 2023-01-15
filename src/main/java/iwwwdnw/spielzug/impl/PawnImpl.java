package iwwwdnw.spielzug.impl;

import iwwwdnw.spielzug.port.Field;

public class PawnImpl  {
	
	private Field currentField;
	
	public PawnImpl(Field startField) {
		this.currentField = startField;
	}
	
	public Field getCurrentField() {
		return currentField;
	}
	
	public void setCurrentField(Field currentField) {
		this.currentField = currentField;
	}
	
}
