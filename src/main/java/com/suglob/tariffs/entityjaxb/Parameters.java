
package com.suglob.tariffs.entityjaxb;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Parameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Parameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="countFavoriteNumbers">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger">
 *               &lt;pattern value="[0-9]"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="tariffing">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="1 second"/>
 *               &lt;enumeration value="12 seconds"/>
 *               &lt;enumeration value="1 minute"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="startPayment" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameters", propOrder = {
    "countFavoriteNumbers",
    "tariffing",
    "startPayment"
})
public class Parameters {

    @XmlElement(required = true)
    protected BigInteger countFavoriteNumbers;
    @XmlElement(required = true)
    protected String tariffing;
    @XmlElement(required = true)
    protected BigDecimal startPayment;

    /**
     * Gets the value of the countFavoriteNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCountFavoriteNumbers() {
        return countFavoriteNumbers;
    }

    /**
     * Sets the value of the countFavoriteNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCountFavoriteNumbers(BigInteger value) {
        this.countFavoriteNumbers = value;
    }

    /**
     * Gets the value of the tariffing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTariffing() {
        return tariffing;
    }

    /**
     * Sets the value of the tariffing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTariffing(String value) {
        this.tariffing = value;
    }

    /**
     * Gets the value of the startPayment property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartPayment() {
        return startPayment;
    }

    /**
     * Sets the value of the startPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartPayment(BigDecimal value) {
        this.startPayment = value;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "countFavoriteNumbers=" + countFavoriteNumbers +
                ", tariffing='" + tariffing + '\'' +
                ", startPayment=" + startPayment +
                '}';
    }
}
