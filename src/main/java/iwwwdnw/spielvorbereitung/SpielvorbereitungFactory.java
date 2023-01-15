package iwwwdnw.spielvorbereitung;

import iwwwdnw.logic.port.SpielvorbereitungPort;

public interface SpielvorbereitungFactory extends SpielvorbereitungPort {
	SpielvorbereitungFactory FACTORY = new SpielvorbereitungFactoryImpl();
}
