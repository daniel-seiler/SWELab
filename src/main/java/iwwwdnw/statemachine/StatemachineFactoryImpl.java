package iwwwdnw.statemachine;

import iwwwdnw.statemachine.impl.StateMachineImpl;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.statemachine.port.Subject;

public class StatemachineFactoryImpl implements StatemachineFactory, StateMachine, Subject {
	private StateMachineImpl stateMachine;
	
	private void makeStateMachine() {
		if (this.stateMachine == null) {
			this.stateMachine = new StateMachineImpl();
		}
	}

	@Override
	public synchronized void attach(Observer obs) {
		this.stateMachine.attach(obs);
	}

	@Override
	public synchronized void detach(Observer obs) {
		this.stateMachine.detach(obs);
	}

	@Override
	public synchronized State getState() {
		return this.stateMachine.getState();
	}
	@Override
	public synchronized void setState(State state) {
		this.stateMachine.setState(state);
	}
}