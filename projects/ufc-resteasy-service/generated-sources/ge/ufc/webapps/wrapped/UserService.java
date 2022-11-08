
package ge.ufc.webapps.wrapped;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "UserService", targetNamespace = "http://ws.webapps.ufc.ge/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserService {


    /**
     * 
     * @param agentTransactionId
     * @return
     *     returns long
     * @throws AgentAccessDeniedException_Exception
     * @throws AgentAuthFailedException_Exception
     * @throws InternalErrorException_Exception
     * @throws TransactionNotFoundException_Exception
     */
    @WebMethod
    @WebResult(name = "system_transaction_id", targetNamespace = "")
    @RequestWrapper(localName = "status", targetNamespace = "http://ws.webapps.ufc.ge/", className = "ge.ufc.webapps.wrapped.Status")
    @ResponseWrapper(localName = "statusResponse", targetNamespace = "http://ws.webapps.ufc.ge/", className = "ge.ufc.webapps.wrapped.StatusResponse")
    @Action(input = "http://ws.webapps.ufc.ge/UserService/statusRequest", output = "http://ws.webapps.ufc.ge/UserService/statusResponse", fault = {
        @FaultAction(className = InternalErrorException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/status/Fault/InternalErrorException"),
        @FaultAction(className = TransactionNotFoundException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/status/Fault/TransactionNotFoundException"),
        @FaultAction(className = AgentAuthFailedException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/status/Fault/AgentAuthFailedException"),
        @FaultAction(className = AgentAccessDeniedException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/status/Fault/AgentAccessDeniedException")
    })
    public long status(
        @WebParam(name = "agent_transaction_id", targetNamespace = "")
        String agentTransactionId)
        throws AgentAccessDeniedException_Exception, AgentAuthFailedException_Exception, InternalErrorException_Exception, TransactionNotFoundException_Exception
    ;

    /**
     * 
     * @param userId
     * @return
     *     returns java.lang.String
     * @throws AgentAccessDeniedException_Exception
     * @throws AgentAuthFailedException_Exception
     * @throws InternalErrorException_Exception
     * @throws UserNotFoundException_Exception
     */
    @WebMethod
    @WebResult(name = "checkResult", targetNamespace = "")
    @RequestWrapper(localName = "check", targetNamespace = "http://ws.webapps.ufc.ge/", className = "ge.ufc.webapps.wrapped.Check")
    @ResponseWrapper(localName = "checkResponse", targetNamespace = "http://ws.webapps.ufc.ge/", className = "ge.ufc.webapps.wrapped.CheckResponse")
    @Action(input = "http://ws.webapps.ufc.ge/UserService/checkRequest", output = "http://ws.webapps.ufc.ge/UserService/checkResponse", fault = {
        @FaultAction(className = UserNotFoundException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/check/Fault/UserNotFoundException"),
        @FaultAction(className = InternalErrorException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/check/Fault/InternalErrorException"),
        @FaultAction(className = AgentAuthFailedException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/check/Fault/AgentAuthFailedException"),
        @FaultAction(className = AgentAccessDeniedException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/check/Fault/AgentAccessDeniedException")
    })
    public String check(
        @WebParam(name = "user_id", targetNamespace = "")
        int userId)
        throws AgentAccessDeniedException_Exception, AgentAuthFailedException_Exception, InternalErrorException_Exception, UserNotFoundException_Exception
    ;

    /**
     * 
     * @param agentTransactionId
     * @param amount
     * @param userId
     * @return
     *     returns long
     * @throws AgentAccessDeniedException_Exception
     * @throws AgentAuthFailedException_Exception
     * @throws AmountNotPositiveException_Exception
     * @throws DuplicateException_Exception
     * @throws InternalErrorException_Exception
     * @throws UserNotFoundException_Exception
     */
    @WebMethod
    @WebResult(name = "system_transaction_id", targetNamespace = "")
    @RequestWrapper(localName = "pay", targetNamespace = "http://ws.webapps.ufc.ge/", className = "ge.ufc.webapps.wrapped.Pay")
    @ResponseWrapper(localName = "payResponse", targetNamespace = "http://ws.webapps.ufc.ge/", className = "ge.ufc.webapps.wrapped.PayResponse")
    @Action(input = "http://ws.webapps.ufc.ge/UserService/payRequest", output = "http://ws.webapps.ufc.ge/UserService/payResponse", fault = {
        @FaultAction(className = InternalErrorException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/pay/Fault/InternalErrorException"),
        @FaultAction(className = AgentAuthFailedException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/pay/Fault/AgentAuthFailedException"),
        @FaultAction(className = AgentAccessDeniedException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/pay/Fault/AgentAccessDeniedException"),
        @FaultAction(className = DuplicateException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/pay/Fault/DuplicateException"),
        @FaultAction(className = UserNotFoundException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/pay/Fault/UserNotFoundException"),
        @FaultAction(className = AmountNotPositiveException_Exception.class, value = "http://ws.webapps.ufc.ge/UserService/pay/Fault/AmountNotPositiveException")
    })
    public long pay(
        @WebParam(name = "agent_transaction_id", targetNamespace = "")
        String agentTransactionId,
        @WebParam(name = "user_id", targetNamespace = "")
        int userId,
        @WebParam(name = "amount", targetNamespace = "")
        double amount)
        throws AgentAccessDeniedException_Exception, AgentAuthFailedException_Exception, AmountNotPositiveException_Exception, DuplicateException_Exception, InternalErrorException_Exception, UserNotFoundException_Exception
    ;

}
