package iwwwdnw.spielzug;

import iwwwdnw.spielzug.port.*;

public class SpielzugFactoryImpl implements SpielzugFactory, SpielzugPort {
    
    @Override
    public SpielzugPort spielzugPort() {
        return this;
    }
    
    @Override
    public Spielzug spielzug() {
        return this.spielzugPort().spielzug();
    }
}
