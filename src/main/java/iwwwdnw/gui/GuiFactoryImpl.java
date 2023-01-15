package iwwwdnw.gui;

import iwwwdnw.gui.port.View;
import iwwwdnw.gui.port.ViewPort;

public class GuiFactoryImpl implements GuiFactory, ViewPort, View {
    
    @Override
    public ViewPort viewPort() {
        return this;
    }
    
    @Override
    public View view() {
        return this;
    }
    
    @Override
    public void startEventLoop() {
        this.viewPort().view().startEventLoop();
    }
}
