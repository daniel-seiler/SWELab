package iwwwdnw.statemachine.port;

public interface State {
    enum S implements State {
		DiceAvailable,
        DiceResult,
        SelectFigureToMove,
		SelectFigureToStartfield,
        FinishTurn;
	}
}
