package iwwwdnw.spielzug;

import iwwwdnw.spielzug.port.SpielzugPort;

public interface SpielzugFactory {
	SpielzugFactory FACTORY = new SpielzugFactoryImpl();
    
    SpielzugPort spielzugPort();
}
