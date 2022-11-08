package ge.ufc.webapps.ws;

import ge.ufc.webapps.exception.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface UserService {

    @WebMethod(operationName = "check")
    @WebResult(name = "checkResult")
    String check(@WebParam(name = "user_id") int id) throws UserNotFoundException, InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException;

    @WebMethod(operationName = "pay")
    @WebResult(name = "system_transaction_id")
    long pay(@WebParam(name = "agent_transaction_id") String agentTransactionId,@WebParam(name = "user_id") int userId,@WebParam(name = "amount") double amount) throws InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException, DuplicateException, UserNotFoundException, AmountNotPositiveException;

    @WebMethod(operationName = "status")
    @WebResult(name = "system_transaction_id")
    long status(@WebParam(name = "agent_transaction_id") String agentTransactionId) throws InternalErrorException, TransactionNotFoundException, AgentAuthFailedException, AgentAccessDeniedException;
}
