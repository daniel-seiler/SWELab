package iwwwdnw.spielzug;

import iwwwdnw.spielzug.port.*;
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
    public TurnInfo movePawn(Field field, Pawn pawn) {
        if (stateMachine.getState() != State.S.SelectFigureToMove)
            return null;
        return this.spielzug().movePawn(field, pawn);
    }
    
    @Override
    public void pawnToStartField(Field startField) {
        if (stateMachine.getState() != State.S.SelectFigureToStartfield)
            return;
        this.spielzug().pawnToStartField(startField);
    }
    
    @Override
    public TurnInfo checkDiceResult() {
        if (stateMachine.getState() != State.S.DiceResult)
            return null;
        return this.spielzug().checkDiceResult();
    }
    
    @Override
    public DiceResult throwDice(Player player) {
        if (stateMachine.getState() != State.S.DiceAvailable)
            return null;
        return this.spielzug().throwDice(player);
    }
}
