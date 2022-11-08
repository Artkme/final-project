package ge.ufc.webapps.job;

import ge.ufc.webapps.dao.PayDaoImpl;
import ge.ufc.webapps.db.DatabaseManager;
import ge.ufc.webapps.model.Pay;
import ge.ufc.webapps.query.Query;
import ge.ufc.webapps.wrapped.InternalErrorException_Exception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.sql.*;

public class PaymentsRetry implements Job {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            final Connection connection = DatabaseManager.getDatabaseConnection();

            try(PreparedStatement ps = connection.prepareStatement(Query.SELECT_ALL_FROM_PAYMENTS_BY_STATUS.getSQL())) {
                ps.setInt(1, 1);
                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Pay pay = new Pay(rs.getString("payment_id"), rs.getInt("user_id"), rs.getDouble("amount"));
                        PayDaoImpl.doPayment(true, pay, connection);
                    }
                }
            }
        } catch (InternalErrorException_Exception | SQLException e) {
            logger.error("Error, getCause: {}", e::getCause);
        }
    }
}
