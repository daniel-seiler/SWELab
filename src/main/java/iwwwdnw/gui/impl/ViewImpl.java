package iwwwdnw.gui.impl;

import iwwwdnw.gui.port.View;
import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.logic.port.SpielzugPort;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;

public class ViewImpl implements View, Observer {
    
    private MVCPort mvcPort;
    private Controller controller;
    private State currentState;
    private boolean running = false;
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
    
    void show(String text) {
        System.out.println("Controller tells me to print this: " + text);
    }
    
    void display() {
        System.out.println("Display result here");
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
