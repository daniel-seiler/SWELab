package iwwwdnw.databaseConnector;

import iwwwdnw.databaseConnector.port.AccessPort;

public class DatabaseConnectorFactoryImpl implements DatabaseConnectorFactory, AccessPort {
    
    @Override
    public AccessPort accessPort() {
        return this;
    }
}
