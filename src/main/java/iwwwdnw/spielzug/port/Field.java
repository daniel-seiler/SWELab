package iwwwdnw.spielzug.port;

public interface Field {
	
	enum FieldType {
		HomeField, BoardFiel, StartField
	}

	FieldType get();
	
	public Integer getFieldID();

}
