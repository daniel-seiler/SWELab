package iwwwdnw.statemachine.port;

import iwwwdnw.statemachine.port.State;

public interface StateMachine {
	
	public void setState(State state);

	public State getState();
}
