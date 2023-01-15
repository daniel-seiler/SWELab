package iwwwdnw.statemachine;

import iwwwdnw.statemachine.port.StateMachinePort;
import iwwwdnw.statemachine.port.SubjectPort;

public interface StatemachineFactory extends StateMachinePort, SubjectPort {
	StatemachineFactory FACTORY = new StatemachineFactoryImpl();
}
