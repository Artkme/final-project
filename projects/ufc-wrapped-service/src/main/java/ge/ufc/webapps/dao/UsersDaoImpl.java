package ge.ufc.webapps.dao;

import ge.ufc.webapps.exception.*;
import ge.ufc.webapps.query.Query;
import ge.ufc.webapps.util.Validator;
import ge.ufc.webapps.ws.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;
import java.sql.*;

public class UsersDaoImpl implements UsersDao {

    private static final String DUPLICATE_KEY_ERROR = "23505";
    private static final Logger logger = LogManager.getLogger();
    private final Connection connection;
    private final HttpServletRequest request = (HttpServletRequest) UserServiceImpl.wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);

    public UsersDaoImpl(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public String checkById(final int user_id) throws UserNotFoundException, InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException {
        Validator.checkValidRequest(request);
        return getUserInitials(user_id);
    }

    @Override
    public long pay(final String agentTransactionId, final int userId, final double amount) throws InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException, DuplicateException, UserNotFoundException, AmountNotPositiveException {
        Validator.checkValidPay(request, userId, amount);
        final int agentId = Integer.parseInt(request.getHeader("agent"));
        return getPayResult(agentId, agentTransactionId, userId, amount);
    }

    @Override
    public long status(final String agentTransactionId) throws InternalErrorException, TransactionNotFoundException, AgentAuthFailedException, AgentAccessDeniedException {
        Validator.checkValidRequest(request);
        return getStatusResult(agentTransactionId);
    }

    private String getUserInitials(final int user_id) throws UserNotFoundException, InternalErrorException {
        try(final PreparedStatement ps = connection.prepareStatement(Query.SELECT_ALL_PERSON_BY_ID.getSQL())) {
            ps.setInt(1, user_id);

            try(final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    final String name = rs.getString("name");
                    final String lastname = rs.getString("lastname");
                    final double balance = rs.getDouble("balance");

                    logger.trace("Check result: {}", formatCheckByIdValue(name, lastname, balance));
                    return formatCheckByIdValue(name, lastname, balance);
                } else {
                    logger.error("User not Found in the Database");
                    throw new UserNotFoundException("User not Found in the Database");
                }
            }
        } catch (final SQLException e) {
            logger.error("Internal error exception. cause: ", e.getCause());
            throw new InternalErrorException("Unable to get user from the database" , e);
        }
    }

    private long getPayResult(final int agentId, final String agentTransactionId, final int userId, final double amount) throws InternalErrorException, DuplicateException {
        try(final PreparedStatement ps = connection.prepareStatement(Query.INSERT_TRANSACTION.getSQL(), Statement.RETURN_GENERATED_KEYS)) {
            return getSystemTransactionId(ps, agentId, agentTransactionId, userId, amount);
        } catch (final SQLException e) {
            if (DUPLICATE_KEY_ERROR.equals(e.getSQLState())) {
                return duplicateMethod(agentId, agentTransactionId, userId, amount);
            }
            logger.error("Internal Error");
            throw new InternalErrorException();
        }
    }

    private long getSystemTransactionId(final PreparedStatement ps, final int agentId, final String agentTransactionId, final int userId, final double amount) throws InternalErrorException, SQLException {
        ps.setInt(1, agentId);
        ps.setString(2, agentTransactionId);
        ps.setInt(3, userId);
        ps.setDouble(4, amount);
        ps.executeUpdate();

        try (final PreparedStatement ps2 = connection.prepareStatement(Query.ADD_USER_BALANCE.getSQL())) {
            ps2.setInt(1, userId);
            ps2.setString(2, agentTransactionId);
            ps2.setInt(3, userId);
            ps2.executeUpdate();
        }

        try (final ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                logger.trace("SYSTEM_TRANSACTION_ID: {}", rs.getLong(1));
                return rs.getLong(1);
            } else {
                logger.error("Internal Error");
                throw new InternalErrorException();
            }
        }
    }

    private long duplicateMethod(final int agentId, final String agentTransactionId, final int userId, final double amount) throws DuplicateException, InternalErrorException {
        try(final PreparedStatement ps = connection.prepareStatement(Query.SELECT_SYSTEM_TRANSACTION_ID_BY_WHERE_CLAUSE.getSQL(), Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, agentTransactionId);
            ps.setInt(2, userId);
            ps.setInt(3, agentId);
            ps.setDouble(4, amount);

            try(final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    logger.trace("SYSTEM_TRANSACTION_ID: {}", rs.getLong(1));
                    return rs.getLong(1);
                } else {
                    logger.error("Duplicate Fault");
                    throw new DuplicateException();
                }
            }
        } catch (final SQLException e) {
            throw new InternalErrorException("internal error", e);
        }
    }

    private long getStatusResult(final String agentTransactionId) throws TransactionNotFoundException, InternalErrorException {
        final int agentId = Integer.parseInt(request.getHeader("agent"));
        try(final PreparedStatement ps = connection.prepareStatement(Query.SELECT_SYS_TRANS_ID_BY_AGENT_TRANS_ID.getSQL())) {
            ps.setString(1, agentTransactionId);
            ps.setInt(2, agentId);

            try(final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    logger.trace("SYSTEM_TRANSACTION_ID: {}", rs.getLong(1));
                    return rs.getLong(1);
                } else {
                    logger.error("Transaction Not found");
                    throw new TransactionNotFoundException();
                }
            }
        } catch (final SQLException e) {
            logger.error("Internal Error. cause: " + e.getCause());
            throw new InternalErrorException("Internal Error", e);
        }
    }

    private String formatCheckByIdValue(final String name, final String lastname, final double balance) {
        return name.charAt(0) + ". " + lastname.charAt(0) + ". " + balance + "₾";
    }
}
