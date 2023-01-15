package iwwwdnw.spielzug;

import iwwwdnw.spielzug.port.SpielzugPort;

public interface SpielzugFactory extends SpielzugPort {
	SpielzugFactory FACTORY = new SpielzugFactoryImpl();
}
