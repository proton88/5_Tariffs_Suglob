package com.suglob.tariffs.sax;

import jaxb.CallTariff;
import jaxb.InternetTariff;
import jaxb.Tariff;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class TariffHandler extends DefaultHandler {
    private Set<Tariff> tariffs;
    private InternetTariff currentInternetTariff = null;
    private CallTariff currentCallTariff = null;
    private TariffEnum currentEnum = null;
    private EnumSet<TariffEnum> withText;

    public TariffHandler() {
        tariffs = new HashSet<Tariff>();
        withText = EnumSet.range(TariffEnum.NAME, TariffEnum.PRICEUPPERTRAFFIC);
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        int i=attrs.getIndex("operatorName");
        int j=attrs.getIndex("rounding");
        if ("internetTariff".equals(localName)) {
            currentInternetTariff = new InternetTariff();
            if (i!=-1){
                currentInternetTariff.setOperatorName(attrs.getValue(i));
            }else{
                currentInternetTariff.setOperatorName("velcom");
            }
            if (j!=-1) {
                currentInternetTariff.setRounding(new BigInteger(attrs.getValue(j)));
            }
            /*if (attrs.getLocalName(0).equals("operatorName")) {// NOT RELY
                currentInternetTariff.setOperatorName(attrs.getValue(0));
                currentInternetTariff.setRounding(new BigInteger(attrs.getValue(1)));
            } else {
                currentInternetTariff.setOperatorName(attrs.getValue(1));
                currentInternetTariff.setRounding(new BigInteger(attrs.getValue(0)));
            }*/
        } else if ("callTariff".equals(localName)) {
            currentCallTariff = new CallTariff();
            if (i!=-1){
                currentCallTariff.setOperatorName(attrs.getValue(i));
            }else{
                currentCallTariff.setOperatorName("velcom");
            }
        } else {
            TariffEnum temp = TariffEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }

    }

    public void endElement(String uri, String localName, String qName) {
        if ("internetTariff".equals(localName)) {
            tariffs.add(currentInternetTariff);
            currentInternetTariff=null;
        }
        if ("callTariff".equals(localName)) {
            tariffs.add(currentCallTariff);
            currentCallTariff=null;
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum){
                case NAME:
                   if (currentCallTariff!=null){
                       currentCallTariff.setName(s);
                   }else{
                       currentInternetTariff.setName(s);
                   }
                   break;
                case PAYROLL:
                    if (currentCallTariff!=null){
                        currentCallTariff.setPayroll(new BigDecimal(s));
                    }else{
                        currentInternetTariff.setPayroll(new BigDecimal(s));
                    }
                    break;
                case FREEMINUTES:
                    currentCallTariff.setFreeMinutes(new BigInteger(s));
                    break;
                case INSIDEPRICE:
                    currentCallTariff.getCallPrices().setInsidePrice(new BigDecimal(s));
                    break;
                case OUTSIDEPRICE:
                    currentCallTariff.getCallPrices().setOutsidePrice(new BigDecimal(s));
                    break;
                case STATIONARYPRICE:
                    currentCallTariff.getCallPrices().setStationaryPrice(new BigDecimal(s));
                    break;
                case SMSPRICE:
                    currentCallTariff.setSmsPrice(new BigDecimal(s));
                    break;
                case COUNTFAVORITENUMBERS:
                    currentCallTariff.getParameters().setCountFavoriteNumbers(new BigInteger(s));
                    break;
                case TARIFFING:
                    currentCallTariff.getParameters().setTariffing(s);
                    break;
                case STARTPAYMENT:
                    currentCallTariff.getParameters().setStartPayment(new BigDecimal(s));
                    break;
                case TRAFFIC:
                    currentInternetTariff.setTraffic(new BigInteger(s));
                    break;
                case PRICEUPPERTRAFFIC:
                    currentInternetTariff.setPriceUpperTraffic(new BigDecimal(s));
                    break;
                default: //////////////////////////////////////////////// myException
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum=null;
    }
}