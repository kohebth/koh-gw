package koh.db.hub;

import lombok.extern.slf4j.Slf4j;
import org.mariadb.jdbc.Configuration;
import org.mariadb.jdbc.HostAddress;
import org.mariadb.jdbc.MariaDbPoolDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
class DatabaseConnectorImpl implements DatabaseConnector {
    static final String HOST;
    static final Integer PORT;
    static final String USER;
    static final String PASSWORD;

    private final Configuration configuration;
    private final MariaDbPoolDataSource dataSource;

    static {
        HOST = "localhost";
        PORT = 3306;
        USER = "root";
        PASSWORD = "dev@123";
    }

    DatabaseConnectorImpl() {
        try {
            log.info("Detected driver: {}", org.mariadb.jdbc.Driver.class.getName());

            configuration = new Configuration.Builder()
                    .addresses(HostAddress.from(HOST, PORT, true))
                    .user(USER)
                    .database("vps_management")
                    .autocommit(false)
                    .connectTimeout(30000)
                    .maxIdleTime(300000)
                    .pool(true)
                    .maxPoolSize(16)
                    .minPoolSize(4)
                    .poolValidMinDelay(1)
                    .build();

            dataSource = new MariaDbPoolDataSource(configuration.initialUrl() + "&password=" + PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to configure DataSource");
        }
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
