
package ge.ufc.webapps.wrapped;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ge.ufc.webapps.wrapped package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AgentAccessDeniedException_QNAME = new QName("http://ws.webapps.ufc.ge/", "AgentAccessDeniedException");
    private final static QName _AgentAuthFailedException_QNAME = new QName("http://ws.webapps.ufc.ge/", "AgentAuthFailedException");
    private final static QName _AmountNotPositiveException_QNAME = new QName("http://ws.webapps.ufc.ge/", "AmountNotPositiveException");
    private final static QName _DuplicateException_QNAME = new QName("http://ws.webapps.ufc.ge/", "DuplicateException");
    private final static QName _InternalErrorException_QNAME = new QName("http://ws.webapps.ufc.ge/", "InternalErrorException");
    private final static QName _TransactionNotFoundException_QNAME = new QName("http://ws.webapps.ufc.ge/", "TransactionNotFoundException");
    private final static QName _UserNotFoundException_QNAME = new QName("http://ws.webapps.ufc.ge/", "UserNotFoundException");
    private final static QName _Check_QNAME = new QName("http://ws.webapps.ufc.ge/", "check");
    private final static QName _CheckResponse_QNAME = new QName("http://ws.webapps.ufc.ge/", "checkResponse");
    private final static QName _Pay_QNAME = new QName("http://ws.webapps.ufc.ge/", "pay");
    private final static QName _PayResponse_QNAME = new QName("http://ws.webapps.ufc.ge/", "payResponse");
    private final static QName _Status_QNAME = new QName("http://ws.webapps.ufc.ge/", "status");
    private final static QName _StatusResponse_QNAME = new QName("http://ws.webapps.ufc.ge/", "statusResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ge.ufc.webapps.wrapped
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AgentAccessDeniedException }
     * 
     * @return
     *     the new instance of {@link AgentAccessDeniedException }
     */
    public AgentAccessDeniedException createAgentAccessDeniedException() {
        return new AgentAccessDeniedException();
    }

    /**
     * Create an instance of {@link AgentAuthFailedException }
     * 
     * @return
     *     the new instance of {@link AgentAuthFailedException }
     */
    public AgentAuthFailedException createAgentAuthFailedException() {
        return new AgentAuthFailedException();
    }

    /**
     * Create an instance of {@link AmountNotPositiveException }
     * 
     * @return
     *     the new instance of {@link AmountNotPositiveException }
     */
    public AmountNotPositiveException createAmountNotPositiveException() {
        return new AmountNotPositiveException();
    }

    /**
     * Create an instance of {@link DuplicateException }
     * 
     * @return
     *     the new instance of {@link DuplicateException }
     */
    public DuplicateException createDuplicateException() {
        return new DuplicateException();
    }

    /**
     * Create an instance of {@link InternalErrorException }
     * 
     * @return
     *     the new instance of {@link InternalErrorException }
     */
    public InternalErrorException createInternalErrorException() {
        return new InternalErrorException();
    }

    /**
     * Create an instance of {@link TransactionNotFoundException }
     * 
     * @return
     *     the new instance of {@link TransactionNotFoundException }
     */
    public TransactionNotFoundException createTransactionNotFoundException() {
        return new TransactionNotFoundException();
    }

    /**
     * Create an instance of {@link UserNotFoundException }
     * 
     * @return
     *     the new instance of {@link UserNotFoundException }
     */
    public UserNotFoundException createUserNotFoundException() {
        return new UserNotFoundException();
    }

    /**
     * Create an instance of {@link Check }
     * 
     * @return
     *     the new instance of {@link Check }
     */
    public Check createCheck() {
        return new Check();
    }

    /**
     * Create an instance of {@link CheckResponse }
     * 
     * @return
     *     the new instance of {@link CheckResponse }
     */
    public CheckResponse createCheckResponse() {
        return new CheckResponse();
    }

    /**
     * Create an instance of {@link Pay }
     * 
     * @return
     *     the new instance of {@link Pay }
     */
    public Pay createPay() {
        return new Pay();
    }

    /**
     * Create an instance of {@link PayResponse }
     * 
     * @return
     *     the new instance of {@link PayResponse }
     */
    public PayResponse createPayResponse() {
        return new PayResponse();
    }

    /**
     * Create an instance of {@link Status }
     * 
     * @return
     *     the new instance of {@link Status }
     */
    public Status createStatus() {
        return new Status();
    }

    /**
     * Create an instance of {@link StatusResponse }
     * 
     * @return
     *     the new instance of {@link StatusResponse }
     */
    public StatusResponse createStatusResponse() {
        return new StatusResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgentAccessDeniedException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgentAccessDeniedException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "AgentAccessDeniedException")
    public JAXBElement<AgentAccessDeniedException> createAgentAccessDeniedException(AgentAccessDeniedException value) {
        return new JAXBElement<>(_AgentAccessDeniedException_QNAME, AgentAccessDeniedException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgentAuthFailedException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgentAuthFailedException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "AgentAuthFailedException")
    public JAXBElement<AgentAuthFailedException> createAgentAuthFailedException(AgentAuthFailedException value) {
        return new JAXBElement<>(_AgentAuthFailedException_QNAME, AgentAuthFailedException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AmountNotPositiveException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AmountNotPositiveException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "AmountNotPositiveException")
    public JAXBElement<AmountNotPositiveException> createAmountNotPositiveException(AmountNotPositiveException value) {
        return new JAXBElement<>(_AmountNotPositiveException_QNAME, AmountNotPositiveException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicateException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DuplicateException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "DuplicateException")
    public JAXBElement<DuplicateException> createDuplicateException(DuplicateException value) {
        return new JAXBElement<>(_DuplicateException_QNAME, DuplicateException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InternalErrorException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InternalErrorException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "InternalErrorException")
    public JAXBElement<InternalErrorException> createInternalErrorException(InternalErrorException value) {
        return new JAXBElement<>(_InternalErrorException_QNAME, InternalErrorException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransactionNotFoundException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransactionNotFoundException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "TransactionNotFoundException")
    public JAXBElement<TransactionNotFoundException> createTransactionNotFoundException(TransactionNotFoundException value) {
        return new JAXBElement<>(_TransactionNotFoundException_QNAME, TransactionNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserNotFoundException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UserNotFoundException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "UserNotFoundException")
    public JAXBElement<UserNotFoundException> createUserNotFoundException(UserNotFoundException value) {
        return new JAXBElement<>(_UserNotFoundException_QNAME, UserNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Check }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Check }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "check")
    public JAXBElement<Check> createCheck(Check value) {
        return new JAXBElement<>(_Check_QNAME, Check.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "checkResponse")
    public JAXBElement<CheckResponse> createCheckResponse(CheckResponse value) {
        return new JAXBElement<>(_CheckResponse_QNAME, CheckResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pay }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Pay }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "pay")
    public JAXBElement<Pay> createPay(Pay value) {
        return new JAXBElement<>(_Pay_QNAME, Pay.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PayResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "payResponse")
    public JAXBElement<PayResponse> createPayResponse(PayResponse value) {
        return new JAXBElement<>(_PayResponse_QNAME, PayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Status }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Status }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "status")
    public JAXBElement<Status> createStatus(Status value) {
        return new JAXBElement<>(_Status_QNAME, Status.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StatusResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "statusResponse")
    public JAXBElement<StatusResponse> createStatusResponse(StatusResponse value) {
        return new JAXBElement<>(_StatusResponse_QNAME, StatusResponse.class, null, value);
    }

}
