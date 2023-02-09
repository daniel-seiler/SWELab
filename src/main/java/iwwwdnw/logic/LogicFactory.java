package iwwwdnw.logic;

import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.logic.port.SpielPort;

public interface LogicFactory {
    LogicFactory FACTORY = new LogicFactoryImpl();
    
    MVCPort MVCPort();
    
    SpielPort spielPort();
    
}
