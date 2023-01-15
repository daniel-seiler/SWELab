package iwwwdnw.spielvorbereitung;

import iwwwdnw.spielvorbereitung.port.SpielvorbereitungPort;

public interface SpielvorbereitungFactory {
	SpielvorbereitungFactory FACTORY = new SpielvorbereitungFactoryImpl();
    
    SpielvorbereitungPort spielvorbereitungPort();
}
