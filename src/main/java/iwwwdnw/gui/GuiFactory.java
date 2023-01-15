package iwwwdnw.gui;

import iwwwdnw.gui.GuiFactoryImpl;

public interface GuiFactory {
	GuiFactory FACTORY = new GuiFactoryImpl();
}
