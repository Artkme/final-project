package ge.ufc.webapps.ws;

import ge.ufc.webapps.model.Pay;
import ge.ufc.webapps.wrapped.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/payments")
public interface PaymentService {

    @GET
    @Path("/get-user/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUser(@PathParam("user_id") int userId) throws AgentAuthFailedException_Exception, InternalErrorException_Exception, AgentAccessDeniedException_Exception, UserNotFoundException_Exception;

    @POST
    @Path("/fill-balance")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response fillBalance(Pay pay) throws AgentAuthFailedException_Exception, InternalErrorException_Exception, AmountNotPositiveException_Exception, DuplicateException_Exception, AgentAccessDeniedException_Exception, UserNotFoundException_Exception;
}
