package com.suglob.tariffs.enums;

public enum TariffEnum {
    TARIFFS("tariffs"),
    CALLTARIFF("callTariff"),
    INTERNETTARIFF("internetTariff"),
    CALLPRICES("callPrices"),
    PARAMETERS("parameters"),
    OPERATORNAME("operatorName"),
    ROUNDING("rounding"),
    NAME("name"),
    PAYROLL("payroll"),
    FREEMINUTES("freeMinutes"),
    INSIDEPRICE("insidePrice"),
    OUTSIDEPRICE("outsidePrice"),
    STATIONARYPRICE("stationaryPrice"),
    SMSPRICE("smsPrice"),
    COUNTFAVORITENUMBERS("countFavoriteNumbers"),
    TARIFFING("tariffing"),
    STARTPAYMENT("startPayment"),
    TRAFFIC("traffic"),
    PRICEUPPERTRAFFIC("priceUpperTraffic");

    private String value;

    public String getValue() {
        return value;
    }

    TariffEnum(String value) {
        this.value = value;
    }
}
