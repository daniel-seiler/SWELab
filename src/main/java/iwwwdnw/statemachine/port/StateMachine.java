package iwwwdnw.statemachine.port;

public interface StateMachine {
	
	public void setState(State state);

	public State getState();

	public static interface Subject {
			void detach(Observer parameter0);
	
			void attach(Observer parameter0);
		}
}
