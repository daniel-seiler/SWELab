package iwwwdnw.spielzug.impl;

import iwwwdnw.spielzug.port.Field;

public class BoardField implements Field {
	
	private final Integer field;
	private final FieldType attr = FieldType.BoardFiel;
	
	public BoardField(int field) {
		this.field = field;
	}
	
	
	@Override
	public FieldType get() {
		return attr;
	}

	@Override
	public Integer getFieldID() {
		return field;
	}

}