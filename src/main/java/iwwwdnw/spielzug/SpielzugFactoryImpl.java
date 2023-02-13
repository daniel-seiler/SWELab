package iwwwdnw.spielzug;

import iwwwdnw.spielzug.impl.PawnImpl;
import iwwwdnw.spielzug.impl.SpielzugImpl;
import iwwwdnw.spielzug.port.*;
import iwwwdnw.statemachine.StatemachineFactory;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.statemachine.port.StateMachinePort;

public class SpielzugFactoryImpl implements SpielzugFactory, SpielzugPort, Spielzug, SpielzugInfo {
    
    private final StateMachinePort stateMachinePort = StatemachineFactory.FACTORY.stateMachinePort();
    private StateMachine stateMachine;
    private SpielzugImpl spielzug;
    
    private void mkSpielzug() {
        if (this.spielzug == null) {
            this.stateMachine = this.stateMachinePort.stateMachine();
            this.spielzug = new SpielzugImpl(this.stateMachinePort);
        }
    }
    
    @Override
    public SpielzugPort spielzugPort() {
        return this;
    }
    
    @Override
    public void finishTurn() {
        if (stateMachine.getState() != State.S.FinishTurn)
            return;
        this.spielzug.finishTurn();
    }
    
    @Override
    public void movePawn(int fieldId, int pawnId) {
        if (stateMachine.getState() != State.S.SelectFigureToMove)
            return;
        this.spielzug.movePawn(fieldId, pawnId);
    }
    
    @Override
    public void pawnToStartField() {
        if (stateMachine.getState() != State.S.SelectFigureToStartfield)
            return;
        this.spielzug.pawnToStartField();
    }
    
    @Override
    public void throwDice() {
        if (stateMachine.getState() != State.S.DiceAvailable)
            return;
        this.spielzug.throwDice();
    }
    
    @Override
    public Spielzug spielzug() {
        this.mkSpielzug();
        return this;
    }
    
    @Override
    public SpielzugInfo spielzugInfo() {
        this.mkSpielzug();
        return this;
    }
    
    @Override
    public String getBoard() {
        return this.spielzug.getBoard();
    }
    
    @Override
    public String getMovementResult() {
        return this.spielzug.getMovementResult();
    }
    
    @Override
    public String getDiceResult() {
        return this.spielzug.getDiceResult();
    }
    
    @Override
    public String currentPlayer() {
        return this.spielzug.currentPlayer();
    }
}
