package iwwwdnw.ui.impl;

import iwwwdnw.logic.port.SpielPort;
import iwwwdnw.ui.impl.commands.Output;
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
        out.append(Output.SEPARATOR);
        
        if (State.S.DiceAvailable.equals(currentState)) {
            out.append(Output.TYPE_THROW);
        } else if (State.S.DiceResult.equals(currentState)) {
            out.append(spielPort.spielzugInfo().getDiceResult());
        } else if (State.S.SelectFigureToMove.equals(currentState)) {
            out.append(spielPort.spielzugInfo().getMovementResult())
                    .append(spielPort.spielzugInfo().getBoard())
                    .append(Output.TYPE_MOVE);
        } else if (State.S.SelectFigureToStartfield.equals(currentState)) {
            out.append(spielPort.spielzugInfo().getBoard())
                    .append(Output.TYPE_MOVE_TO_START);
        } else if (State.S.FinishTurn.equals(currentState)) {
            out.append(spielPort.spielzugInfo().getBoard())
                    .append(Output.FINISHED_TURN)
                    .append(spielPort.spielzugInfo().currentPlayer())
                    .append(Output.TYPE_NEXT);
        } else {
            out.append(Output.INVALID_INPUT);
        }
        out.append(Output.ASK_FOR_COMMAND);
        
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

