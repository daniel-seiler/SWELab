package iwwwdnw.databaseConnector;

import iwwwdnw.databaseConnector.port.AccessPort;

public interface DatabaseConnectorFactory extends AccessPort {
	DatabaseConnectorFactory FACTORY = new DatabaseConnectorFactoryImpl();
}
