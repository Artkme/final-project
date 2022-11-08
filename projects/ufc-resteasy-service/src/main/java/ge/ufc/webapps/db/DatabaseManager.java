package ge.ufc.webapps.db;

import ge.ufc.webapps.wrapped.InternalErrorException;
import ge.ufc.webapps.wrapped.InternalErrorException_Exception;
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

    private static final String PAYMENT_TOMCAT_DS = "java:comp/env/jdbc/paymentDS";

    public static Connection getDatabaseConnection() throws InternalErrorException_Exception {
        return getConnection();
    }

    private static Connection getConnection() throws InternalErrorException_Exception {
        try {
            DataSource ds = getDataSource(PAYMENT_TOMCAT_DS);
            return ds.getConnection();
        } catch (NamingException e) {
            throw new InternalErrorException_Exception("Unable to find datasource", new InternalErrorException());
        } catch (SQLException e) {
            throw new InternalErrorException_Exception("Unable to connect to the database", new InternalErrorException());
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