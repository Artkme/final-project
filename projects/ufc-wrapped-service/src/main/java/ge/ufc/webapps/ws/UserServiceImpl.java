package ge.ufc.webapps.ws;

import ge.ufc.webapps.dao.UsersDao;
import ge.ufc.webapps.dao.UsersDaoImpl;
import ge.ufc.webapps.db.DatabaseManager;
import ge.ufc.webapps.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.sql.Connection;


@WebService(endpointInterface = "ge.ufc.webapps.ws.UserService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();
    private Connection connection;
    private UsersDao usersDao;

    @Resource
    public static WebServiceContext wsContext;

    @Override
    public String check(final int id) throws UserNotFoundException, InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException {
        try {
            logger.info("Opening database connection");
            connection = DatabaseManager.getDatabaseConnection();
            usersDao = new UsersDaoImpl(connection);
            logger.info("Getting person initials from the database, user_id: {}", id);

            return usersDao.checkById(id);
        } finally {
            logger.info("Closing database connection");
            DatabaseManager.close(connection);
        }
    }

    @Override
    public long pay(final String agentTransactionId, final int userId, final double amount) throws InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException, DuplicateException, UserNotFoundException, AmountNotPositiveException {
        try {
            logger.info("Opening database connection");
            connection = DatabaseManager.getDatabaseConnection();
            usersDao = new UsersDaoImpl(connection);
            logger.info("Getting system transaction ID from the database, user_id: {}, agent_transaction_id: {}", userId, agentTransactionId);

            return usersDao.pay(agentTransactionId, userId, amount);
        } finally {
            logger.info("Closing database connection");
            DatabaseManager.close(connection);
        }
    }

    @Override
    public long status(final String agentTransactionId) throws InternalErrorException, TransactionNotFoundException, AgentAuthFailedException, AgentAccessDeniedException {
        try{
            logger.info("Opening database connection");
            connection = DatabaseManager.getDatabaseConnection();
            usersDao = new UsersDaoImpl(connection);
            logger.info("Getting system transaction ID from the database, agent_transaction_id: {}", agentTransactionId);

            return usersDao.status(agentTransactionId);
        } finally {
            logger.info("Closing database connection");
            DatabaseManager.close(connection);
        }
    }
}
