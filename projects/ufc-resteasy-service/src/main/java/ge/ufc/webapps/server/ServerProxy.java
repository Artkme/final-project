package ge.ufc.webapps.server;

import com.sun.xml.ws.client.BindingProviderProperties;
import ge.ufc.webapps.conf.Authentication;
import ge.ufc.webapps.conf.ConfManager;
import ge.ufc.webapps.wrapped.*;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.handler.MessageContext;
import java.util.*;

public class ServerProxy {

    private static UserService userServiceProxy;

    static {
        loadProxy();
    }

    public String check(int userId) throws AgentAuthFailedException_Exception, InternalErrorException_Exception, AgentAccessDeniedException_Exception, UserNotFoundException_Exception {
        return userServiceProxy.check(userId);
    }

    public long pay(String agentTransId, int userId,  double amount) throws AgentAuthFailedException_Exception, InternalErrorException_Exception, AmountNotPositiveException_Exception, DuplicateException_Exception, AgentAccessDeniedException_Exception, UserNotFoundException_Exception {
        return userServiceProxy.pay(agentTransId, userId, amount);
    }

    private static void loadProxy() {
        try {
            Authentication auth = ConfManager.getConfiguration().getAuth();
            UserServiceImplService userServiceService = new UserServiceImplService();
            userServiceProxy = userServiceService.getUserServiceImplPort();
            BindingProvider bindingProvider = (BindingProvider) userServiceProxy;
            bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, auth.getUrl());
            bindingProvider.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, auth.getReadTimeout());
            bindingProvider.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, auth.getConnectTimeout());

            Map<String, List<String>> headers = new HashMap<>();
            headers.put("agent", Collections.singletonList(auth.getAgentId()));
            headers.put("pass", Collections.singletonList(auth.getPassword()));
            bindingProvider.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, headers);


            Binding binding = bindingProvider.getBinding();
            var handlerList = binding.getHandlerChain();
            if (handlerList == null) {
                handlerList = new ArrayList<>();
            }
            handlerList.add(new SoapHandler());
            binding.setHandlerChain(handlerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
