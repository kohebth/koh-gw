package koh.db.hub;

import lombok.NonNull;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.SettingsTools;
import org.jooq.impl.DAOImpl;
import org.jooq.impl.DSL;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class DSLContextFactory {
    private static final DatabaseConnector CONNECTOR = new MariaDBConnector();

    @NonNull
    public static DSLContext useContext()
            throws SQLException {
        return DSL.using(CONNECTOR.getDataSource(), SQLDialect.MARIADB, SettingsTools.defaultSettings());
    }

    @NonNull
    public static <T extends DAOImpl<?, ?, ?>> T useDao(Class<T> daoClass) {
        try {
            T daoInstant = daoClass.getDeclaredConstructor().newInstance();
            daoInstant.setConfiguration(useContext().configuration());
            return daoInstant;
        } catch (SQLException e) {
            throw new DSLContextException(e);
        } catch (NoSuchMethodException| IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new DAOInstantiationException(e);
        }
    }

    static class DSLContextException extends RuntimeException {
        DSLContextException(SQLException e) {
            super(e);
        }
    }

    static class DAOInstantiationException extends RuntimeException {
        DAOInstantiationException(Exception e) {
            super(e);
        }
    }
}
