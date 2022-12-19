package iwwwdnw.statemachine.impl;

import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachine;

public class StateMachineImpl implements StateMachine {
	private State currentState;
	
	public StateMachineImpl() {
		this.currentState = State.S.TurnStarted;
	}
	
	@Override
	public State getState() {
		return this.currentState;
	}
	
	@Override
	public void setState(State state) {
		this.currentState = state;
	}
}

