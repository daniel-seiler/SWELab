package iwwwdnw.ui;

import iwwwdnw.logic.port.SpielPort;
import iwwwdnw.ui.impl.UiImpl;
import iwwwdnw.ui.port.Ui;
import iwwwdnw.ui.port.UiPort;
import iwwwdnw.logic.LogicFactory;
import iwwwdnw.logic.port.MVCPort;

public class UiFactoryImpl implements UiFactory, UiPort, Ui {
    private SpielPort spielPort = LogicFactory.FACTORY.spielPort();
    private MVCPort mvcPort = LogicFactory.FACTORY.MVCPort();
    UiImpl ui;
    
    private void mkUI() {
        if (ui == null)
            ui = new UiImpl(mvcPort, spielPort);
    }
   
    @Override
    public void startEventLoop() {
        mkUI();
        ui.startEventLoop();
    }
    
    @Override
    public UiPort uiPort() {
        return this;
    }
    
    @Override
    public Ui ui() {
        this.mkUI();
        return this;
    }
}
