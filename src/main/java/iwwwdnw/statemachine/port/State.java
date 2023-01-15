package iwwwdnw.statemachine.port;

public interface State {

	public enum S implements State {
		DiceAvailable, DiceResult, SelectFigureToMove, 
		SelectFigureToStartfield, FinishTurn;
	}
}
