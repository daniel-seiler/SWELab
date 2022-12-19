package iwwwdnw.statemachine.port;

public interface State {

	public enum S implements State {
		TurnStarted, DiceResult, FurtherMoves, TurnCompleted;
	}
}
