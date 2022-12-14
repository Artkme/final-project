
package ge.ufc.webapps.wrapped;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "TransactionNotFoundException", targetNamespace = "http://ws.webapps.ufc.ge/")
public class TransactionNotFoundException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private TransactionNotFoundException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public TransactionNotFoundException_Exception(String message, TransactionNotFoundException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public TransactionNotFoundException_Exception(String message, TransactionNotFoundException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ge.ufc.webapps.wrapped.TransactionNotFoundException
     */
    public TransactionNotFoundException getFaultInfo() {
        return faultInfo;
    }

}
