package iwwwdnw.spielzug.impl;

import iwwwdnw.spielzug.port.Field;

public class HomeField implements Field {
	
	private final FieldType attr = FieldType.HomeField;
	
	@Override
	public FieldType get() {
		return attr;
	}

	@Override
	public Integer getFieldID() {
		return null;
	}

}
