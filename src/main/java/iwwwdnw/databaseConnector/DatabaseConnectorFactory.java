package iwwwdnw.databaseConnector;

import iwwwdnw.databaseConnector.port.AccessPort;

public interface DatabaseConnectorFactory {
	DatabaseConnectorFactory FACTORY = new DatabaseConnectorFactoryImpl();
    
    AccessPort accessPort();
}
