
package com.suglob.tariffs.entityjaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.suglob.tariffs.entityjaxb package.
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

    private final static QName _InternetTariff_QNAME = new QName("http://www.example.org/tariffs", "internetTariff");
    private final static QName _Tariff_QNAME = new QName("http://www.example.org/tariffs", "tariff");
    private final static QName _CallTariff_QNAME = new QName("http://www.example.org/tariffs", "callTariff");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.suglob.tariffs.entityjaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InternetTariff }
     * 
     */
    public InternetTariff createInternetTariff() {
        return new InternetTariff();
    }

    /**
     * Create an instance of {@link Tariff }
     * 
     */
    public Tariff createTariff() {
        return new Tariff();
    }

    /**
     * Create an instance of {@link CallTariff }
     * 
     */
    public CallTariff createCallTariff() {
        return new CallTariff();
    }

    /**
     * Create an instance of {@link Tariffs }
     * 
     */
    public Tariffs createTariffs() {
        return new Tariffs();
    }

    /**
     * Create an instance of {@link Parameters }
     * 
     */
    public Parameters createParameters() {
        return new Parameters();
    }

    /**
     * Create an instance of {@link CallPrices }
     * 
     */
    public CallPrices createCallPrices() {
        return new CallPrices();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InternetTariff }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/tariffs", name = "internetTariff", substitutionHeadNamespace = "http://www.example.org/tariffs", substitutionHeadName = "tariff")
    public JAXBElement<InternetTariff> createInternetTariff(InternetTariff value) {
        return new JAXBElement<InternetTariff>(_InternetTariff_QNAME, InternetTariff.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tariff }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/tariffs", name = "tariff")
    public JAXBElement<Tariff> createTariff(Tariff value) {
        return new JAXBElement<Tariff>(_Tariff_QNAME, Tariff.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallTariff }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/tariffs", name = "callTariff", substitutionHeadNamespace = "http://www.example.org/tariffs", substitutionHeadName = "tariff")
    public JAXBElement<CallTariff> createCallTariff(CallTariff value) {
        return new JAXBElement<CallTariff>(_CallTariff_QNAME, CallTariff.class, null, value);
    }

    @Override
    public String toString() {
        return "ObjectFactory{}";
    }
}
