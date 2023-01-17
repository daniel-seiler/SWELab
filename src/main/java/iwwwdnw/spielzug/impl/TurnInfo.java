package iwwwdnw.spielzug.impl;
public class TurnInfo {
	
	private final Boolean success;
	
	TurnInfo(Boolean success) {
		this.success = success;
	}
    
    @Override
    public String toString() {
        //TODO
        return success ? "Success" : "No success";
    }
}
