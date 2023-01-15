package iwwwdnw.logic;

import iwwwdnw.logic.LogicFactoryImpl;
import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.logic.port.SpielvorbereitungPort;
import iwwwdnw.logic.port.SpielzugPort;

public interface LogicFactory {
	LogicFactory FACTORY = new LogicFactoryImpl();
    
    MVCPort mvcPort();
    
    SpielvorbereitungPort spielvorbereitungPort();
    
    SpielzugPort spielzugPort();
}
