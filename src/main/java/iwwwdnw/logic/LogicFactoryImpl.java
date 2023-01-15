package iwwwdnw.logic;

import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.logic.port.SpielvorbereitungPort;
import iwwwdnw.logic.port.SpielzugPort;
import iwwwdnw.spielvorbereitung.port.Spielvorbereitung;
import iwwwdnw.spielzug.port.Spielzug;
import iwwwdnw.statemachine.StatemachineFactory;
import iwwwdnw.statemachine.port.Subject;
import iwwwdnw.statemachine.port.SubjectPort;

public class LogicFactoryImpl implements LogicFactory, MVCPort, SpielvorbereitungPort, SpielzugPort {
    private SubjectPort subjectPort = StatemachineFactory.FACTORY.subjectPort();
    
    @Override
    public MVCPort mvcPort() {
        return this;
    }
    
    @Override
    public SpielvorbereitungPort spielvorbereitungPort() {
        return this;
    }
    
    @Override
    public SpielzugPort spielzugPort() {
        return this;
    }
    
    @Override
    public Subject subject() {
        return this.subjectPort.subject();
    }
    
    @Override
    public Spielvorbereitung spielvorbereitung() {
        return this.spielvorbereitungPort().spielvorbereitung();
    }
    
    @Override
    public Spielzug spielzug() {
        return this.spielzugPort().spielzug();
    }
}
