package iwwwdnw.statemachine;

import iwwwdnw.statemachine.impl.StateMachineImpl;
import iwwwdnw.statemachine.port.*;
import iwwwdnw.statemachine.port.StateMachine;

public class StatemachineFactoryImpl implements StatemachineFactory, StateMachinePort, StateMachine {
    private StateMachineImpl stateMachine;
    
    private StateMachineImpl getStateMachine() {
        if (this.stateMachine == null)
            this.stateMachine = new StateMachineImpl();
        return this.stateMachine;
    }
    @Override
    public SubjectPort subjectPort() {
        return this;
    }
    
    @Override
    public StateMachinePort stateMachinePort() {
        return this;
    }
    
    @Override
    public void setState(State state) {
        this.stateMachine.setState(state);
    }
    
    @Override
    public State getState() {
        return this.stateMachine.getState();
    }
    
    @Override
    public StateMachine stateMachine() {
        return this.getStateMachine();
    }
    
    @Override
    public void attach(Observer obs) {
        this.stateMachine.attach(obs);
    }
    
    @Override
    public void detach(Observer obs) {
        this.stateMachine.detach(obs);
    }
    
    @Override
    public iwwwdnw.statemachine.port.StateMachine.Subject subject() {
        return this.getStateMachine();
    }
}