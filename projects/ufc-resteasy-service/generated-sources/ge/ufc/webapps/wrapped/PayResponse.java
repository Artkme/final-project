
package ge.ufc.webapps.wrapped;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for payResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="payResponse">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="system_transaction_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payResponse", propOrder = {
    "systemTransactionId"
})
public class PayResponse {

    @XmlElement(name = "system_transaction_id")
    protected long systemTransactionId;

    /**
     * Gets the value of the systemTransactionId property.
     * 
     */
    public long getSystemTransactionId() {
        return systemTransactionId;
    }

    /**
     * Sets the value of the systemTransactionId property.
     * 
     */
    public void setSystemTransactionId(long value) {
        this.systemTransactionId = value;
    }

}
