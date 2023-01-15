package iwwwdnw.gui;

import iwwwdnw.gui.port.ViewPort;

public interface GuiFactory {
	GuiFactory FACTORY = new GuiFactoryImpl();
    
    ViewPort viewPort();
}
