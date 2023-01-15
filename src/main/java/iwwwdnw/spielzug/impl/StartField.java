package iwwwdnw.spielzug.impl;

import iwwwdnw.spielzug.port.Field;

public class StartField implements Field {
	
	private final Integer field;
	private final FieldType attr = FieldType.StartField;
	
	public StartField(int field) {
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
