package iwwwdnw.ui.impl;

import iwwwdnw.logic.port.SpielPort;
import iwwwdnw.ui.port.Ui;
import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.spielzug.port.SpielzugPort;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;

public class UiImpl implements Ui, Observer {
    
    private MVCPort mvcPort;
    private Controller controller;
    private State currentState;
    private boolean running = true;
    private SpielzugPort spielzugPort;
    //TODO change spielzugPort to spielPort
    
    public UiImpl(MVCPort mvcPort, SpielPort spielPort) {
        this.spielzugPort = spielPort;
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
        out.append("-----------------------------------\n");
        
        if (State.S.DiceAvailable.equals(currentState)) {
            out.append("Start your next turn by throwing the dice\n" +
                    "TYPE [*] for help\n" +
                    "TYPE [throw] to throw the dices:");
        } else if (State.S.DiceResult.equals(currentState)) {
            out.append(spielzugPort.spielzugInfo().getDiceResult());
        } else if (State.S.SelectFigureToMove.equals(currentState)) {
            out.append(spielzugPort.spielzugInfo().getBoard());
            out.append("Move a pawn [move {pawn_nr} {field_nr}: ");
        } else if (State.S.SelectFigureToStartfield.equals(currentState)) {
            out.append("Move a pawn to a start field: TYPE [moveToStart]: ");
        } else if (State.S.FinishTurn.equals(currentState)) {
            out.append("YOU FINISHED YOUR TURN...\n");
            out.append("Next player:");
            out.append(spielzugPort.spielzugInfo().currentPlayer());
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

