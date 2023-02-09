package iwwwdnw.logic;

import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.logic.port.SpielPort;
import iwwwdnw.spielzug.SpielzugFactory;
import iwwwdnw.spielzug.port.Spielzug;
import iwwwdnw.spielzug.port.SpielzugPort;
import iwwwdnw.statemachine.StatemachineFactory;
import iwwwdnw.statemachine.port.Subject;
import iwwwdnw.statemachine.port.SubjectPort;

public class LogicFactoryImpl implements LogicFactory, MVCPort, SpielPort {
    
    private SubjectPort subjectPort = StatemachineFactory.FACTORY.subjectPort();
    private SpielzugPort spielzugPort = SpielzugFactory.FACTORY.spielzugPort();
    
    @Override
    public MVCPort MVCPort() {
        return this;
    }
    
    @Override
    public SpielPort spielPort() {
        return this;
    }
    
    @Override
    public Subject subject() {
        return this.subjectPort.subject();
    }
    
    @Override
    public Spielzug spielzug() {
        return this.spielzugPort.spielzug();
    }
}
