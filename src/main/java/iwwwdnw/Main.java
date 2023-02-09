package iwwwdnw;

import iwwwdnw.ui.UiFactory;

public class Main {

	public static void main(String[] args) {
        UiFactory.FACTORY.uiPort().ui().startEventLoop();
	}

}
