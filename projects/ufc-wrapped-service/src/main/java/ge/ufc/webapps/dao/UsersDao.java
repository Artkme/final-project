package ge.ufc.webapps.dao;

import ge.ufc.webapps.exception.*;


public interface UsersDao {

    String checkById(int user_id) throws UserNotFoundException, InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException;

    long pay(String agentTransactionId, int userId, double amount) throws InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException, DuplicateException, UserNotFoundException, AmountNotPositiveException;

    long status(String agentTransactionId) throws InternalErrorException, TransactionNotFoundException, AgentAuthFailedException, AgentAccessDeniedException;
}
