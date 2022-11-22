package iwwwdnw.comp1;

import iwwwdnw.comp1.port.PortA;

public interface Factory {

	Factory FACTORY = new Facade();

	PortA portA();

}
