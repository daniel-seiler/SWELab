
package iwwwdnw.spielzug.impl;

import iwwwdnw.spielzug.port.Field;

public class HomeField implements Field {

	@Override
	public FieldType get() {
		return attr;
	}

	@Override
	public Integer getFieldID() {
		return null;
	}

}
