package iwwwdnw.spielzug;

import iwwwdnw.spielzug.impl.*;
import iwwwdnw.spielzug.port.Field;
import iwwwdnw.spielzug.port.Spielzug;
import iwwwdnw.spielzug.port.SpielzugPort;
import iwwwdnw.statemachine.StatemachineFactory;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachinePort;
import iwwwdnw.statemachine.port.StateMachine;



public class SpielzugFactoryImpl implements SpielzugFactory, SpielzugPort, Spielzug {
    private final StateMachinePort stateMachinePort  = StatemachineFactory.FACTORY.stateMachinePort();
    private StateMachine stateMachine;
    
    @Override
    public SpielzugPort spielzugPort() {
        return this;
    }
    
    @Override
    public Spielzug spielzug() {
        return this;
    }
    
    @Override
    public void finishTurn() {
        if (stateMachine.getState() != State.S.FinishTurn)
            return;
        this.spielzug().finishTurn();
    }
    
    @Override
    public TurnInfo movePawn(Field field, PawnImpl pawn) {
        if (stateMachine.getState() != State.S.SelectFigureToMove)
            return null;
        return this.spielzug().movePawn(field, pawn);
    }
    
    @Override
    public void pawnToStartField() {
        if (stateMachine.getState() != State.S.SelectFigureToStartfield)
            return;
        this.spielzug().pawnToStartField();
    }
    
    @Override
    public DiceResult throwDice() {
        if (stateMachine.getState() != State.S.DiceAvailable)
            return null;
        return this.spielzug().throwDice();
    }
}
