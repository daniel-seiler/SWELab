package iwwwdnw.gui;

import iwwwdnw.gui.impl.ViewImpl;
import iwwwdnw.gui.port.View;
import iwwwdnw.gui.port.ViewPort;
import iwwwdnw.logic.LogicFactory;
import iwwwdnw.logic.port.MVCPort;
import iwwwdnw.spielzug.port.SpielzugPort;

public class GuiFactoryImpl implements GuiFactory, ViewPort, View {
    private SpielzugPort spielzugPort = LogicFactory.FACTORY.spielzugPort();
    private MVCPort mvcPort = LogicFactory.FACTORY.mvcPort();
    ViewImpl ui;
    
    private void mkUI() {
        if (ui == null)
            ui = new ViewImpl(mvcPort, spielzugPort);
    }
    
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
        mkUI();
        ui.startEventLoop();
    }
}
