package iwwwdnw.ui.impl;

import iwwwdnw.logic.port.SpielPort;
import iwwwdnw.ui.port.Ui;
import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;

public class UiImpl implements Ui, Observer {
    
    private final MVCPort mvcPort;
    private final Controller controller;
    private State currentState;
    private boolean running = true;
    private final SpielPort spielPort;
    
    public UiImpl(MVCPort mvcPort, SpielPort spielPort) {
        this.spielPort = spielPort;
        this.mvcPort = mvcPort;
        this.mvcPort.subject().attach(this);
        this.init();
        this.controller = new Controller(this, mvcPort, spielPort);
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
        out.append("-----------------------------------\n");
        out.append("Current player: ");
        out.append(spielPort.spielzugInfo().currentPlayer());
        out.append("\n");
        if (State.S.DiceAvailable.equals(currentState)) {
            out.append("\nTYPE [throw] to throw the dices: ");
            out.append(spielPort.spielzugInfo().getDiceResult());
        } else if (State.S.DiceResult.equals(currentState)) {
            out.append(spielPort.spielzugInfo().getDiceResult());
        } else if (State.S.SelectFigureToMove.equals(currentState)) {
            out.append(spielPort.spielzugInfo().getBoard());
            out.append("Move a pawn [move {pawn_nr} {field_nr}: ");
        } else if (State.S.SelectFigureToStartfield.equals(currentState)) {
            out.append("Move a pawn to a start field: TYPE [moveToStart]: ");
        } else if (State.S.FinishTurn.equals(currentState)) {
            out.append("YOU FINISHED YOUR TURN...\n");
            out.append("\nTYPE [next] to start turn: ");
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

