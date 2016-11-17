package com.suglob.tariffs.dom;

import jaxb.CallPrices;
import jaxb.CallTariff;
import jaxb.InternetTariff;
import jaxb.Tariff;
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
    private Set<Tariff> tariffs;
    private DocumentBuilder docBuilder;

    public TariffsDomBuilder(){
        tariffs=new HashSet<>();
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
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
            NodeList internetTariffList = root.getElementsByTagName("internetTariff");
            for (int i = 0; i < internetTariffList.getLength(); i++) {
                Element tariffElement = (Element) internetTariffList.item(i);
                InternetTariff tariff = buildInternetTariff(tariffElement);
                tariffs.add(tariff);
            }
            NodeList callTariffList = root.getElementsByTagName("callTariff");
            for (int i = 0; i < callTariffList.getLength(); i++) {
                Element tariffElement = (Element) callTariffList.item(i);
                CallTariff tariff = buildCallTariff(tariffElement);
                tariffs.add(tariff);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private InternetTariff buildInternetTariff(Element tariffElement) {
        InternetTariff internetTariff = new InternetTariff();
        if (tariffElement.getAttribute("operatorName") != "") {
            internetTariff.setOperatorName(tariffElement.getAttribute("operatorName"));
        } else {
            internetTariff.setOperatorName("velcom");
        }
        if (tariffElement.getAttribute("rounding") != "") {
            internetTariff.setRounding(new BigInteger(tariffElement.getAttribute("rounding")));
        }
        internetTariff.setName(getElementTextContent(tariffElement, "name"));
        internetTariff.setPayroll(new BigDecimal(getElementTextContent(tariffElement, "payroll")));
        internetTariff.setTraffic(new BigInteger(getElementTextContent(tariffElement, "traffic")));
        internetTariff.setPriceUpperTraffic(new BigDecimal(
                getElementTextContent(tariffElement, "priceUpperTraffic")));
        return internetTariff;
    }

    private CallTariff buildCallTariff(Element tariffElement) {
        CallTariff callTariff=new CallTariff();
        if (tariffElement.getAttribute("operatorName") != "") {
            callTariff.setOperatorName(tariffElement.getAttribute("operatorName"));
        } else {
            callTariff.setOperatorName("velcom");
        }
        callTariff.setName(getElementTextContent(tariffElement, "name"));
        callTariff.setPayroll(new BigDecimal(getElementTextContent(tariffElement, "payroll")));
        callTariff.setSmsPrice(new BigDecimal(getElementTextContent(tariffElement, "smsPrice")));
        callTariff.setFreeMinutes(new BigInteger(getElementTextContent(tariffElement, "freeMinutes")));

        Element callPricesElement=(Element)tariffElement.getElementsByTagName("callPrices").item(0);
        callTariff.getCallPrices().setInsidePrice(new BigDecimal(
                getElementTextContent(callPricesElement, "insidePrice")));
        callTariff.getCallPrices().setOutsidePrice(new BigDecimal(
                getElementTextContent(callPricesElement, "outsidePrice")));
        callTariff.getCallPrices().setStationaryPrice(new BigDecimal(
                getElementTextContent(callPricesElement, "stationaryPrice")));

        Element parametersElement=(Element)tariffElement.getElementsByTagName("parameters").item(0);
        callTariff.getParameters().setCountFavoriteNumbers(new BigInteger(
                getElementTextContent(parametersElement,"countFavoriteNumbers")));
        callTariff.getParameters().setStartPayment(new BigDecimal(
                getElementTextContent(parametersElement,"startPayment")));
        callTariff.getParameters().setTariffing(getElementTextContent(parametersElement, "tariffing"));

        return callTariff;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
