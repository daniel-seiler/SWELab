package iwwwdnw.spielvorbereitung;

import iwwwdnw.spielvorbereitung.port.SpielvorbereitungPort;
import iwwwdnw.spielvorbereitung.port.Spielvorbereitung;

public class SpielvorbereitungFactoryImpl implements SpielvorbereitungFactory, SpielvorbereitungPort {
    
    @Override
    public SpielvorbereitungPort spielvorbereitungPort() {
        return this;
    }
    
    @Override
    public Spielvorbereitung spielvorbereitung() {
        return this.spielvorbereitungPort().spielvorbereitung();
    }
}
