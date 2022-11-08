package ge.ufc.webapps.dao;


import ge.ufc.webapps.model.Pay;
import javax.ws.rs.core.Response;

public interface PayDao {

    Response getUser(int userId);
    Response fillBalance(Pay pay);
}
