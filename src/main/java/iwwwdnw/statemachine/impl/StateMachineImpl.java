package iwwwdnw.statemachine.impl;

import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.statemachine.port.Subject;
import iwwwdnw.statemachine.port.Observer;

import java.util.ArrayList;
import java.util.List;



public class StateMachineImpl implements StateMachine, Subject {
	private State currentState;
	private List<Observer> observers = new ArrayList<>();
	
	public StateMachineImpl() {
		this.currentState = State.S.DiceAvailable;
	}
	
	@Override
	public void attach(Observer obs) {
		this.observers.add(obs);
		obs.update(this.currentState);
	}

	@Override
	public void detach(Observer obs) {
		this.observers.remove(obs);
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

