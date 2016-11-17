
package jaxb;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InternetTariff complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InternetTariff">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/tariffs}Tariff">
 *       &lt;sequence>
 *         &lt;element name="traffic" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="priceUpperTraffic" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *       &lt;attribute name="rounding" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InternetTariff", propOrder = {
    "traffic",
    "priceUpperTraffic"
})
public class InternetTariff
    extends Tariff
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger traffic;
    @XmlElement(required = true)
    protected BigDecimal priceUpperTraffic;
    @XmlAttribute(name = "rounding")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger rounding;

    /**
     * Gets the value of the traffic property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTraffic() {
        return traffic;
    }

    /**
     * Sets the value of the traffic property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTraffic(BigInteger value) {
        this.traffic = value;
    }

    /**
     * Gets the value of the priceUpperTraffic property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriceUpperTraffic() {
        return priceUpperTraffic;
    }

    /**
     * Sets the value of the priceUpperTraffic property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriceUpperTraffic(BigDecimal value) {
        this.priceUpperTraffic = value;
    }

    /**
     * Gets the value of the rounding property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRounding() {
        return rounding;
    }

    /**
     * Sets the value of the rounding property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRounding(BigInteger value) {
        this.rounding = value;
    }

    @Override
    public String toString() {
        return super.toString()+"InternetTariff{" +
                "traffic=" + traffic +
                ", priceUpperTraffic=" + priceUpperTraffic +
                ", rounding=" + rounding +
                '}'+"\n";
    }
}
