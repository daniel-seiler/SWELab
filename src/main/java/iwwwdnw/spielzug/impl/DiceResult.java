package iwwwdnw.spielzug.impl;
public class DiceResult {

	private int result;
	
	public DiceResult(int result) {
		this.result = result;
	}
	
	public int getResult() {
		return result;
	}
	
	public void sub(int sub) {
		result -= sub;
	}
	
	public Boolean isZero () {
		return result == 0;
	}
}
