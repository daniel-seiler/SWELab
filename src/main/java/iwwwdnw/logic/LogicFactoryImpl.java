package iwwwdnw.logic;

import iwwwdnw.spielzug.port.DiceResult;
import iwwwdnw.spielzug.port.Field;
import iwwwdnw.spielzug.port.Pawn;
import iwwwdnw.spielzug.port.Player;
import iwwwdnw.spielzug.port.TurnInfo;
import iwwwdnw.statemachine.StatemachineFactory;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.SubjectPort;

public class LogicFactoryImpl implements LogicFactory {

	@Override
	public void detach(Observer parameter0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attach(Observer parameter0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TurnInfo movePawn(Field field, Pawn pawn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pawnToStartField(Field startField) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TurnInfo checkDiceResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiceResult throwDice(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

}
