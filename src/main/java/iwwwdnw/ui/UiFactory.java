package iwwwdnw.ui;

import iwwwdnw.ui.port.UiPort;

public interface UiFactory {
	UiFactory FACTORY = new UiFactoryImpl();
    
    UiPort uiPort();
}
