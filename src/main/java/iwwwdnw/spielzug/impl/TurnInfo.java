package iwwwdnw.spielzug.impl;
public class TurnInfo {
	
	private final Boolean success;
	
	TurnInfo(Boolean success) {
		this.success = success;
	}
    
    @Override
    public String toString() {
        //TODO duell started, moves left, ...
        return success ? "Success" : "No success";
    }
}
