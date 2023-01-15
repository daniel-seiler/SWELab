package iwwwdnw.statemachine;

import iwwwdnw.statemachine.impl.StateMachineImpl;
import iwwwdnw.statemachine.port.*;

public class StatemachineFactoryImpl implements StatemachineFactory, SubjectPort, StateMachinePort, StateMachine, Subject {
    
    private StateMachineImpl stateMachine;
    private void mkStateMachine() {
        if (this.stateMachine == null)
            this.stateMachine = new StateMachineImpl();
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
    public StateMachine stateMachine() {
        this.mkStateMachine();
        return this;
    }
    
    @Override
    public Subject subject() {
        this.mkStateMachine();
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
    public void detach(Observer obs) {
        this.stateMachine.detach(obs);
    }
    
    @Override
    public void attach(Observer obs) {
        this.stateMachine.attach(obs);
    }
}