package com.suglob.tariffs.stax;

import com.suglob.tariffs.entityjaxb.*;
import com.suglob.tariffs.enums.TariffEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class TariffsStAXBuilder {
    public static final Logger LOGGER = LogManager.getLogger(TariffsStAXBuilder.class);

    public static final String OPERATOR_NAME="velcom";

    private HashSet<Tariff> tariffs=new HashSet<>();
    private XMLInputFactory inputFactory;
    public TariffsStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Tariff> getTariffs(){
        return tariffs;
    }

    public void buildSetTariffs(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (TariffEnum.valueOf(name.toUpperCase()) == TariffEnum.CALLTARIFF) {
                        CallTariff tariff = buildCallTariff(reader);
                        LOGGER.log(Level.INFO,tariff.toString());
                        tariffs.add(tariff);
                    }
                    if (TariffEnum.valueOf(name.toUpperCase()) == TariffEnum.INTERNETTARIFF) {
                        InternetTariff tariff = buildInternetTariff(reader);
                        LOGGER.log(Level.INFO,tariff.toString());
                        tariffs.add(tariff);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            LOGGER.log(Level.ERROR, "StAX parsing error! ", ex);
        } catch (FileNotFoundException ex) {
            LOGGER.fatal("File error ", ex);
            throw new RuntimeException("File not found");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Impossible close file "+fileName+" : ", e);
            }
        }
    }

    private InternetTariff buildInternetTariff(XMLStreamReader reader) throws XMLStreamException {
        InternetTariff tariff=new InternetTariff();
        String operatorName;
        String rounding;
        if ((operatorName=reader.getAttributeValue(null, TariffEnum.OPERATORNAME.getValue()))!=null) {
            tariff.setOperatorName(operatorName);
        }else{
            tariff.setOperatorName(OPERATOR_NAME);
        }

        if ((rounding=reader.getAttributeValue(null, TariffEnum.ROUNDING.getValue()))!=null) {
            tariff.setRounding(new BigInteger(rounding));
        }

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            tariff.setName(getXMLText(reader));
                            break;
                        case PAYROLL:
                            tariff.setPayroll(new BigDecimal(getXMLText(reader)));
                            break;
                        case TRAFFIC:
                            tariff.setTraffic(new BigInteger(getXMLText(reader)));
                            break;
                        case PRICEUPPERTRAFFIC:
                            tariff.setPriceUpperTraffic(new BigDecimal(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffEnum.valueOf(name.toUpperCase()) == TariffEnum.INTERNETTARIFF) {
                        return tariff;
                    }
                    break;
            }
        }
        LOGGER.log(Level.ERROR, "Unknown element in tag internetTariff");
        throw new XMLStreamException("Unknown element in tag internetTariff");


    }

    private CallTariff buildCallTariff(XMLStreamReader reader) throws XMLStreamException {
        CallTariff tariff=new CallTariff();
        String operatorName;
        if ((operatorName=reader.getAttributeValue(null, TariffEnum.OPERATORNAME.getValue()))!=null) {
            tariff.setOperatorName(operatorName);
        }else{
            tariff.setOperatorName(OPERATOR_NAME);
        }

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            tariff.setName(getXMLText(reader));
                            break;
                        case PAYROLL:
                            tariff.setPayroll(new BigDecimal(getXMLText(reader)));
                            break;
                        case FREEMINUTES:
                            tariff.setFreeMinutes(new BigInteger(getXMLText(reader)));
                            break;
                        case CALLPRICES:
                            tariff.setCallPrices(getXMLCallPrices(reader));
                            break;
                        case SMSPRICE:
                            tariff.setSmsPrice(new BigDecimal(getXMLText(reader)));
                            break;
                        case PARAMETERS:
                            tariff.setParameters(getXMLParameters(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffEnum.valueOf(name.toUpperCase()) == TariffEnum.CALLTARIFF) {
                        return tariff;
                    }
                    break;
            }
        }
        LOGGER.log(Level.ERROR, "Unknown element in tag callTariff");
        throw new XMLStreamException("Unknown element in tag callTariff");
    }

    private CallPrices getXMLCallPrices(XMLStreamReader reader) throws XMLStreamException {
        CallPrices prices = new CallPrices();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffEnum.valueOf(name.toUpperCase())) {
                        case INSIDEPRICE:
                            prices.setInsidePrice(new BigDecimal(getXMLText(reader)));
                            break;
                        case OUTSIDEPRICE:
                            prices.setOutsidePrice(new BigDecimal(getXMLText(reader)));
                            break;
                        case STATIONARYPRICE:
                            prices.setStationaryPrice(new BigDecimal(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffEnum.valueOf(name.toUpperCase()) == TariffEnum.CALLPRICES){
                        return prices;
                    }
                    break;
            }
        }
        LOGGER.log(Level.ERROR, "Unknown element in tag callPrices");
        throw new XMLStreamException("Unknown element in tag callPrices");
    }

    private Parameters getXMLParameters(XMLStreamReader reader) throws XMLStreamException {
        Parameters parameters = new Parameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffEnum.valueOf(name.toUpperCase())) {
                        case COUNTFAVORITENUMBERS:
                            parameters.setCountFavoriteNumbers(new BigInteger(getXMLText(reader)));
                            break;
                        case TARIFFING:
                            parameters.setTariffing(getXMLText(reader));
                            break;
                        case STARTPAYMENT:
                            parameters.setStartPayment(new BigDecimal(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffEnum.valueOf(name.toUpperCase()) == TariffEnum.PARAMETERS){
                        return parameters;
                    }
                    break;
            }
        }
        LOGGER.log(Level.ERROR, "Unknown element in tag parameters");
        throw new XMLStreamException("Unknown element in tag parameters");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
