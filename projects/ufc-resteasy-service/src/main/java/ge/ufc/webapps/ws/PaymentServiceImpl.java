package ge.ufc.webapps.ws;

import ge.ufc.webapps.dao.PayDao;
import ge.ufc.webapps.dao.PayDaoImpl;
import ge.ufc.webapps.db.DatabaseManager;
import ge.ufc.webapps.model.Pay;
import ge.ufc.webapps.wrapped.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import javax.ws.rs.core.Response;
import java.sql.*;

public class PaymentServiceImpl implements PaymentService {
    private Connection connection;
    private static PayDao payDao;
    private static final Logger logger = LogManager.getLogger();

    static {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public Response getUser(int userId) {
        payDao = new PayDaoImpl();
        logger.info("Getting person info from the Soap Client, user_id: {}", userId);
        return payDao.getUser(userId);
    }

    @Override
    public Response fillBalance(Pay pay) {
        try {
            logger.info("Opening database connection");
            connection = DatabaseManager.getDatabaseConnection();
            payDao = new PayDaoImpl(connection);
            logger.info("Filling Person's Balance, user_id: {}", pay.getUserId());

            return payDao.fillBalance(pay);
        } catch (InternalErrorException_Exception e) {
            logger.error("Internal Server Error, cause: {}", e::getCause);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            logger.info("Closing database connection");
            DatabaseManager.close(connection);
        }
    }
}
