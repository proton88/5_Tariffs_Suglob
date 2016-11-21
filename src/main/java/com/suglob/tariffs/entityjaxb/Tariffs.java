
package com.suglob.tariffs.entityjaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.example.org/tariffs}tariff" maxOccurs="unbounded" minOccurs="16"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tariff"
})
@XmlRootElement(name = "tariffs")
public class Tariffs {

    @XmlElementRef(name = "tariff", namespace = "http://www.example.org/tariffs", type = JAXBElement.class)
    protected List<JAXBElement<? extends Tariff>> tariff;

    /**
     * Gets the value of the tariff property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tariff property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTariff().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link InternetTariff }{@code >}
     * {@link JAXBElement }{@code <}{@link Tariff }{@code >}
     * {@link JAXBElement }{@code <}{@link CallTariff }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends Tariff>> getTariff() {
        if (tariff == null) {
            tariff = new ArrayList<JAXBElement<? extends Tariff>>();
        }
        return this.tariff;
    }

    @Override
    public String toString() {
        return "Tariffs{" +
                "tariff=" + tariff +
                '}';
    }
}
