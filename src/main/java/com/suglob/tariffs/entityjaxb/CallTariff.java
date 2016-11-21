
package com.suglob.tariffs.entityjaxb;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CallTariff complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CallTariff">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/tariffs}Tariff">
 *       &lt;sequence>
 *         &lt;element name="freeMinutes" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="callPrices" type="{http://www.example.org/tariffs}CallPrices"/>
 *         &lt;element name="smsPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="parameters" type="{http://www.example.org/tariffs}Parameters"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallTariff", propOrder = {
    "freeMinutes",
    "callPrices",
    "smsPrice",
    "parameters"
})
public class CallTariff
    extends Tariff
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger freeMinutes;
    @XmlElement(required = true)
    protected CallPrices callPrices;
    @XmlElement(required = true)
    protected BigDecimal smsPrice;
    @XmlElement(required = true)
    protected Parameters parameters;

    /**
     * Gets the value of the freeMinutes property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFreeMinutes() {
        return freeMinutes;
    }

    /**
     * Sets the value of the freeMinutes property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFreeMinutes(BigInteger value) {
        this.freeMinutes = value;
    }

    /**
     * Gets the value of the callPrices property.
     * 
     * @return
     *     possible object is
     *     {@link CallPrices }
     *     
     */
    public CallPrices getCallPrices() {
        if (callPrices==null) {
            callPrices = new CallPrices();
        }
        return callPrices;
    }

    /**
     * Sets the value of the callPrices property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallPrices }
     *     
     */
    public void setCallPrices(CallPrices value) {
        this.callPrices = value;
    }

    /**
     * Gets the value of the smsPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSmsPrice() {
        return smsPrice;
    }

    /**
     * Sets the value of the smsPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSmsPrice(BigDecimal value) {
        this.smsPrice = value;
    }

    /**
     * Gets the value of the parameters property.
     * 
     * @return
     *     possible object is
     *     {@link Parameters }
     *     
     */
    public Parameters getParameters() {
        if (parameters==null){
            parameters=new Parameters();
        }
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parameters }
     *     
     */
    public void setParameters(Parameters value) {
        this.parameters = value;
    }

    @Override
    public String toString() {
        return super.toString()+"CallTariff{" +
                "freeMinutes=" + freeMinutes +
                ", callPrices=" + callPrices +
                ", smsPrice=" + smsPrice +
                ", parameters=" + parameters +
                '}'+"\n";
    }
}
