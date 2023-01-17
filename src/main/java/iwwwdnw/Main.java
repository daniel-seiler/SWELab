package iwwwdnw;

import iwwwdnw.gui.GuiFactory;

public class Main {

	public static void main(String[] args) {
        GuiFactory.FACTORY.viewPort().view().startEventLoop();
	}

}
