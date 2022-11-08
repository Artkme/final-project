package ge.ufc.webapps.util;

import ge.ufc.webapps.db.DatabaseManager;
import ge.ufc.webapps.exception.*;
import ge.ufc.webapps.query.Query;
import ge.ufc.webapps.ws.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class Validator {

    private final static String USER_IP = getRemoteAddress();
    private static Connection connection;

    public static void checkValidRequest(HttpServletRequest request) throws InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException {
        try {
            connection = DatabaseManager.getDatabaseConnection();
            int agentId = Integer.parseInt(request.getHeader("agent"));
            final String password;
            try {
                password = new String(Base64.getDecoder().decode(request.getHeader("pass")));
            } catch (Exception e) {
                throw new AgentAuthFailedException();
            }
            checkAgentById(agentId, password);
        } catch (InternalErrorException e) {
            throw new InternalErrorException("Internal Error", e);
        } finally {
            DatabaseManager.close(connection);
        }
    }

    public static void checkValidPay(HttpServletRequest request, int userId, double amount) throws InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException, UserNotFoundException, AmountNotPositiveException {
        checkValidRequest(request);
        checkPayParams(userId, amount);
    }

    private static void checkPayParams(int userId, double amount) throws AmountNotPositiveException, UserNotFoundException, InternalErrorException {
        connection = DatabaseManager.getDatabaseConnection();
        if (amount <= 0) {
            throw new AmountNotPositiveException();
        }
        try(PreparedStatement ps = connection.prepareStatement(Query.SELECT_USER_BY_ID.getSQL())) {
            ps.setInt(1, userId);
            try(ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new UserNotFoundException();
                }
            }
        } catch (SQLException e) {
            throw new InternalErrorException();
        }
    }


    private static void checkAgentById(int agentId, String password) throws AgentAccessDeniedException, AgentAuthFailedException, InternalErrorException {
        try(PreparedStatement ps = connection.prepareStatement(Query.SELECT_AGENT_BY_ID_AND_PASSWORD.getSQL())) {
            ps.setInt(1, agentId);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    checkAllowedIpByAgentId(agentId);
                } else {
                    throw new AgentAuthFailedException();
                }
            }
        } catch (SQLException e) {
            throw new InternalErrorException("Internal Error", e);
        }
    }

    private static void checkAllowedIpByAgentId(int agentId) throws AgentAccessDeniedException, InternalErrorException {
        try(PreparedStatement ps2 = connection.prepareStatement(Query.SELECT_ALLOWED_IP_BY_AGENT_ID.getSQL())) {
            ps2.setInt(1, agentId);

            try(ResultSet rs2 = ps2.executeQuery()) {

                if (rs2.next()) {
                    String allowedIp = rs2.getString("allowed_ip");
                    if (!USER_IP.equals(allowedIp)) {
                        throw new AgentAccessDeniedException();
                    }
                } else {
                    throw new AgentAccessDeniedException();
                }
            }
        } catch (SQLException e) {
            throw new InternalErrorException("Internal Error", e);
        }
    }

    private static String getRemoteAddress() {

        return ((HttpServletRequest) UserServiceImpl.wsContext.getMessageContext()
                .get(MessageContext.SERVLET_REQUEST))
                .getRemoteAddr();
    }
}
