package iwwwdnw.logic;

import iwwwdnw.logic.LogicFactoryImpl;
import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.logic.port.SpielvorbereitungPort;
import iwwwdnw.logic.port.SpielzugPort;

public interface LogicFactory extends MVCPort, SpielvorbereitungPort, SpielzugPort {
	LogicFactory FACTORY = new LogicFactoryImpl();
}
