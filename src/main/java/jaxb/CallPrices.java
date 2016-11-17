
package jaxb;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CallPrices complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CallPrices">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="insidePrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="outsidePrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="stationaryPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallPrices", propOrder = {

})
public class CallPrices {

    @XmlElement(required = true)
    protected BigDecimal insidePrice;
    @XmlElement(required = true)
    protected BigDecimal outsidePrice;
    @XmlElement(required = true)
    protected BigDecimal stationaryPrice;

    /**
     * Gets the value of the insidePrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInsidePrice() {
        return insidePrice;
    }

    /**
     * Sets the value of the insidePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInsidePrice(BigDecimal value) {
        this.insidePrice = value;
    }

    /**
     * Gets the value of the outsidePrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOutsidePrice() {
        return outsidePrice;
    }

    /**
     * Sets the value of the outsidePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOutsidePrice(BigDecimal value) {
        this.outsidePrice = value;
    }

    /**
     * Gets the value of the stationaryPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStationaryPrice() {
        return stationaryPrice;
    }

    /**
     * Sets the value of the stationaryPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStationaryPrice(BigDecimal value) {
        this.stationaryPrice = value;
    }

    @Override
    public String toString() {
        return "CallPrices{" +
                "insidePrice=" + insidePrice +
                ", outsidePrice=" + outsidePrice +
                ", stationaryPrice=" + stationaryPrice +
                '}';
    }
}
