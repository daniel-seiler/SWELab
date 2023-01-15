package iwwwdnw.statemachine;

import iwwwdnw.statemachine.port.StateMachinePort;
import iwwwdnw.statemachine.port.SubjectPort;

public interface StatemachineFactory {
	StatemachineFactory FACTORY = new StatemachineFactoryImpl();
	
	SubjectPort subjectPort();
	
	StateMachinePort stateMachinePort();
}
