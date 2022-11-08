package ge.ufc.webapps.db;

import ge.ufc.webapps.exception.InternalErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private static final Logger logger = LogManager.getLogger();

    private static final String USER_TOMCAT_DS = "java:comp/env/jdbc/userDS";

    public static Connection getDatabaseConnection() throws InternalErrorException {
        return getConnection();
    }

    private static Connection getConnection() throws InternalErrorException {
        try {
            DataSource ds = getDataSource(USER_TOMCAT_DS);
            return ds.getConnection();
        } catch (NamingException e) {
            throw new InternalErrorException("Unable to find datasource", e);
        } catch (SQLException e) {
            throw new InternalErrorException("Unable to connect to the database", e);
        }
    }

    private static DataSource getDataSource(String jndiName) throws NamingException {
        Context initCtx = new InitialContext();
        return (DataSource) initCtx.lookup(jndiName);
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn("Unable to close connection", e);
            }
        }
    }
}