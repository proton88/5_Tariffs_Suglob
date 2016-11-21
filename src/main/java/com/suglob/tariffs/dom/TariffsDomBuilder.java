package com.suglob.tariffs.dom;

import com.suglob.tariffs.entityjaxb.CallTariff;
import com.suglob.tariffs.entityjaxb.InternetTariff;
import com.suglob.tariffs.entityjaxb.Tariff;
import com.suglob.tariffs.enums.TariffEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class TariffsDomBuilder {

    public static final Logger LOGGER = LogManager.getLogger(TariffsDomBuilder.class);

    public static final String OPERATOR_NAME="velcom";

    private Set<Tariff> tariffs;
    private DocumentBuilder docBuilder;

    public TariffsDomBuilder(){
        tariffs=new HashSet<>();
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, "Dom Parser Configuration error",e);
        }
    }

    public Set<Tariff> getTariffs(){
        return tariffs;
    }

    public void buildSetTariffs(String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList internetTariffList = root.getElementsByTagName(TariffEnum.INTERNETTARIFF.getValue());
            for (int i = 0; i < internetTariffList.getLength(); i++) {
                Element tariffElement = (Element) internetTariffList.item(i);
                InternetTariff tariff = buildInternetTariff(tariffElement);
                LOGGER.log(Level.INFO,tariff.toString());
                tariffs.add(tariff);
            }
            NodeList callTariffList = root.getElementsByTagName(TariffEnum.CALLTARIFF.getValue());
            for (int i = 0; i < callTariffList.getLength(); i++) {
                Element tariffElement = (Element) callTariffList.item(i);
                CallTariff tariff = buildCallTariff(tariffElement);
                LOGGER.log(Level.INFO,tariff.toString());
                tariffs.add(tariff);
            }
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "File error or I/O error: " ,e);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "Parsing failure: " ,e);
        }
    }

    private InternetTariff buildInternetTariff(Element tariffElement) {
        InternetTariff internetTariff = new InternetTariff();
        if (tariffElement.getAttribute(TariffEnum.OPERATORNAME.getValue()) != "") {
            internetTariff.setOperatorName(tariffElement.getAttribute(TariffEnum.OPERATORNAME.getValue()));
        } else {
            internetTariff.setOperatorName(OPERATOR_NAME);
        }
        if (tariffElement.getAttribute(TariffEnum.ROUNDING.getValue()) != "") {
            internetTariff.setRounding(new BigInteger(tariffElement.getAttribute(TariffEnum.ROUNDING.getValue())));
        }
        internetTariff.setName(getElementTextContent(tariffElement, TariffEnum.NAME.getValue()));
        internetTariff.setPayroll(new BigDecimal(getElementTextContent(tariffElement, TariffEnum.PAYROLL.getValue())));
        internetTariff.setTraffic(new BigInteger(getElementTextContent(tariffElement, TariffEnum.TRAFFIC.getValue())));
        internetTariff.setPriceUpperTraffic(new BigDecimal(
                getElementTextContent(tariffElement, TariffEnum.PRICEUPPERTRAFFIC.getValue())));
        return internetTariff;
    }

    private CallTariff buildCallTariff(Element tariffElement) {
        CallTariff callTariff=new CallTariff();
        if (tariffElement.getAttribute(TariffEnum.OPERATORNAME.getValue()) != "") {
            callTariff.setOperatorName(tariffElement.getAttribute(TariffEnum.OPERATORNAME.getValue()));
        } else {
            callTariff.setOperatorName(OPERATOR_NAME);
        }
        callTariff.setName(getElementTextContent(tariffElement, TariffEnum.NAME.getValue()));
        callTariff.setPayroll(new BigDecimal(getElementTextContent(tariffElement, TariffEnum.PAYROLL.getValue())));
        callTariff.setSmsPrice(new BigDecimal(getElementTextContent(tariffElement, TariffEnum.SMSPRICE.getValue())));
        callTariff.setFreeMinutes(new BigInteger(getElementTextContent(tariffElement, TariffEnum.FREEMINUTES.getValue())));

        Element callPricesElement=(Element)tariffElement.getElementsByTagName(TariffEnum.CALLPRICES.getValue()).item(0);
        callTariff.getCallPrices().setInsidePrice(new BigDecimal(
                getElementTextContent(callPricesElement, TariffEnum.INSIDEPRICE.getValue())));
        callTariff.getCallPrices().setOutsidePrice(new BigDecimal(
                getElementTextContent(callPricesElement, TariffEnum.OUTSIDEPRICE.getValue())));
        callTariff.getCallPrices().setStationaryPrice(new BigDecimal(
                getElementTextContent(callPricesElement, TariffEnum.STATIONARYPRICE.getValue())));

        Element parametersElement=(Element)tariffElement.getElementsByTagName(TariffEnum.PARAMETERS.getValue()).item(0);
        callTariff.getParameters().setCountFavoriteNumbers(new BigInteger(
                getElementTextContent(parametersElement,TariffEnum.COUNTFAVORITENUMBERS.getValue())));
        callTariff.getParameters().setStartPayment(new BigDecimal(
                getElementTextContent(parametersElement,TariffEnum.STARTPAYMENT.getValue())));
        callTariff.getParameters().setTariffing(getElementTextContent(parametersElement, TariffEnum.TARIFFING.getValue()));

        return callTariff;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
