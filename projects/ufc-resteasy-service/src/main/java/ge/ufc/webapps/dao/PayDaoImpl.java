package ge.ufc.webapps.dao;

import ge.ufc.webapps.query.Query;
import ge.ufc.webapps.server.ServerProxy;
import ge.ufc.webapps.model.FillBalanceResponse;
import ge.ufc.webapps.model.Pay;
import ge.ufc.webapps.model.User;
import ge.ufc.webapps.wrapped.*;
import jakarta.xml.ws.WebServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.Response;
import java.net.SocketTimeoutException;
import java.sql.*;
import java.time.LocalDateTime;

public class PayDaoImpl implements PayDao {

    private Connection connection;
    private static ServerProxy serverProxy;

    private static final Logger logger = LogManager.getLogger();

    public PayDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public PayDaoImpl() {
    }

    @Override
    public Response getUser(int userId) {
        User user;
        try {
            serverProxy = new ServerProxy();
            String[] userInitials = serverProxy.check(userId).split("\\s+");
            String fullName = userInitials[0] + userInitials[1];
            double balance = Double.parseDouble(userInitials[2].replace("₾", ""));
            user = new User(fullName, balance);

            logger.trace("getUser result: {}", user.toString());
        } catch (AgentAuthFailedException_Exception | InternalErrorException_Exception | AgentAccessDeniedException_Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (UserNotFoundException_Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (WebServiceException e) {
            if(e.getCause() != null && e.getCause().getClass().isAssignableFrom(SocketTimeoutException.class))
                return Response.status(Response.Status.REQUEST_TIMEOUT).build();
            else
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(user).build();
    }

    @Override
    public Response fillBalance(Pay pay) {
        Response respFillBalance = doFillBalance(connection, pay);
        logger.trace("fillBalance result: {}", respFillBalance);
        return respFillBalance;
    }

    private static Response doFillBalance(Connection connection, Pay pay) {
        try (PreparedStatement ps = connection.prepareStatement(Query.SELECT_ALL_FROM_PAYMENTS_BY_PAYMENT_ID.getSQL())) {
            ps.setInt(1, Integer.parseInt(pay.getPaymentId()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    double amount = rs.getDouble("amount");
                    if (userId != pay.getUserId() || amount != pay.getAmount()) {
                        return Response.status(Response.Status.CONFLICT).build();
                    }

                    short status = (short) rs.getInt("status");
                    short code = (short) rs.getInt("code");
                    if (status == 1) {
                        return doPayment(true, pay, connection);
                    }
                    return Response.status(code).build();
                }
                return doPayment(false, pay, connection);
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    public static Response doPayment(boolean update, Pay pay, Connection connection) throws SQLException {
        serverProxy = new ServerProxy();
        Timestamp reqDate = Timestamp.valueOf(LocalDateTime.now());
        Timestamp respDate;
        long sysTransId = 0;
        short status = 0;
        short code = 200;

        try {
            sysTransId = serverProxy.pay(pay.getPaymentId(), pay.getUserId(), pay.getAmount());
        } catch (InternalErrorException_Exception e) {
            code = 500;
            status = 1;
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AmountNotPositiveException_Exception e) {
            code = 400;
            status = 2;
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (DuplicateException_Exception e) {
            code = 409;
            status = 2;
            return Response.status(Response.Status.CONFLICT).build();
        } catch (UserNotFoundException_Exception e) {
            code = 404;
            status = 2;
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (AgentAuthFailedException_Exception | AgentAccessDeniedException_Exception e) {
            code = 500;
            status = 2;
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (WebServiceException e) {
            status = 1;
            if(e.getCause() != null && e.getCause().getClass().isAssignableFrom(SocketTimeoutException.class)) {
                code = 408;
                return Response.status(Response.Status.REQUEST_TIMEOUT).build();
            }
            code = 500;
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (update) {
                try (PreparedStatement ps = connection.prepareStatement(Query.UPDATE_PAYMENTS_BY_PAYMENT_ID.getSQL())) {
                    logger.info("Update Payments table, payment_id: {}", pay.getPaymentId());
                    ps.setShort(1, status);
                    ps.setShort(2, code);
                    ps.setLong(3, sysTransId);
                    ps.setTimestamp(4, reqDate);
                    respDate = Timestamp.valueOf(LocalDateTime.now());
                    ps.setTimestamp(5, respDate);
                    ps.setInt(6, Integer.parseInt(pay.getPaymentId()));
                    ps.executeUpdate();
                }
            } else {
                respDate = Timestamp.valueOf(LocalDateTime.now());
                insertToPayments(connection, pay, sysTransId, reqDate, respDate, code, status);
            }
        }
        return Response.status(Response.Status.OK).entity(new FillBalanceResponse(sysTransId, code)).build();
    }

    private static void insertToPayments(Connection connection, Pay pay, long sysTransId, Timestamp reqDate, Timestamp respDate, short code, short status) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(Query.INSERT_PAYMENTS.getSQL())) {
            ps.setInt(1, Integer.parseInt(pay.getPaymentId()));
            ps.setInt(2, pay.getUserId());
            ps.setDouble(3, pay.getAmount());
            ps.setLong(4, sysTransId);
            ps.setTimestamp(5, reqDate);
            ps.setTimestamp(6, respDate);
            ps.setShort(7, code);
            ps.setShort(8, status);
            ps.executeUpdate();
        }
    }
}
