package koh.db.hub;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.SettingsTools;
import org.jooq.impl.DSL;

import java.sql.SQLException;

public class DSLContextFactory {
    private static final DatabaseConnector CONNECTOR = new DatabaseConnectorImpl();

    public static DSLContext getContext()
            throws SQLException {
        return DSL.using(CONNECTOR.getDataSource(), SQLDialect.MARIADB, SettingsTools.defaultSettings());
    }
}
