package com.suglob.tariffs.sax;

import com.suglob.tariffs.entityjaxb.CallTariff;
import com.suglob.tariffs.entityjaxb.InternetTariff;
import com.suglob.tariffs.entityjaxb.Tariff;
import com.suglob.tariffs.enums.TariffEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class TariffHandler extends DefaultHandler {
    public static final Logger LOGGER = LogManager.getLogger(TariffHandler.class);

    public static final String OPERATOR_NAME="velcom";

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
        int i=attrs.getIndex(TariffEnum.OPERATORNAME.getValue());
        int j=attrs.getIndex(TariffEnum.ROUNDING.getValue());
        if (TariffEnum.INTERNETTARIFF.getValue().equals(localName)) {
            currentInternetTariff = new InternetTariff();
            if (i!=-1){
                currentInternetTariff.setOperatorName(attrs.getValue(i));
            }else{
                currentInternetTariff.setOperatorName(OPERATOR_NAME);
            }
            if (j!=-1) {
                currentInternetTariff.setRounding(new BigInteger(attrs.getValue(j)));
            }
        } else if (TariffEnum.CALLTARIFF.getValue().equals(localName)) {
            currentCallTariff = new CallTariff();
            if (i!=-1){
                currentCallTariff.setOperatorName(attrs.getValue(i));
            }else{
                currentCallTariff.setOperatorName(OPERATOR_NAME);
            }
        } else {
            TariffEnum temp = TariffEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }

    }

    public void endElement(String uri, String localName, String qName) {
        if (TariffEnum.INTERNETTARIFF.getValue().equals(localName)) {
            LOGGER.log(Level.INFO,currentInternetTariff.toString());
            tariffs.add(currentInternetTariff);
            currentInternetTariff=null;
        }
        if (TariffEnum.CALLTARIFF.getValue().equals(localName)) {
            LOGGER.log(Level.INFO,currentCallTariff.toString());
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
                default:
                    LOGGER.log(Level.ERROR,"Wrong tag name in xml: "+currentEnum.getValue());
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum=null;
    }
}