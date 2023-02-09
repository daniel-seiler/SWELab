package iwwwdnw.gui.impl;

import iwwwdnw.gui.port.View;
import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.spielzug.port.SpielzugPort;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;

public class ViewImpl implements View, Observer {
    
    private MVCPort mvcPort;
    private Controller controller;
    private State currentState;
    private boolean running = true;
    private SpielzugPort spielzugPort;
    
    public ViewImpl(MVCPort mvcPort, SpielzugPort spielzugPort) {
        this.spielzugPort = spielzugPort;
        this.mvcPort = mvcPort;
        this.mvcPort.subject().attach(this);
        this.init();
        this.controller = new Controller(this, mvcPort, spielzugPort);
    }
    
    
    private void init() {
        System.out.println("Game has started");
    }
    
    @Override
    public void update(State state) {
        this.currentState = state;
    }
    
    void show(String out) {
        System.out.println(out);
    }
    
    void display() {
        StringBuilder out = new StringBuilder();
        out.append("-----------------------------------");
        
        if (State.S.DiceAvailable.equals(currentState)) {
        
        } else if (State.S.DiceResult.equals(currentState)) {
        
        } else if (State.S.SelectFigureToMove.equals(currentState)) {
        
        } else if (State.S.SelectFigureToStartfield.equals(currentState)) {
        
        } else if (State.S.FinishTurn.equals(currentState)) {
        
        } else {
            throw new RuntimeException("Illegal state");
        }
        
        show(out.toString());
    }
    
    public MVCPort getMvcPort() {
        return mvcPort;
    }
    
    
    void stop() {
        this.running = false;
    }
    
    @Override
    public void startEventLoop() {
        while (running) {
            this.display();
            this.controller.doit();
        }
    }
}

