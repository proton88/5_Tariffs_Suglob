package com.suglob.tariffs;

import com.suglob.tariffs.dom.TariffsDomBuilder;
import com.suglob.tariffs.sax.TariffsSAXBuilder;
import jaxb.*;
import jaxb.Tariff;

import javax.xml.bind.JAXBElement;
import java.math.BigDecimal;


public class Run {
    public static void main(String[] args) {
        /*Tariffs tariffs=new Tariffs();
        jaxb.Tariff tariff=new Tariff();
        tariff.setName("Hello");
        tariff.setOperatorName("velcom");
        tariff.setPayroll(new BigDecimal(4.5));
        tariffs.getTariff().add(new ObjectFactory().createTariff(tariff));
        System.out.println(tariffs.getTariff().get(0).getValue());*/

        /*TariffsSAXBuilder saxBuilder=new TariffsSAXBuilder();
        saxBuilder.buildSetStudents("src/main/resources/xmlxsd/tariffs.xml");
        System.out.println(saxBuilder.getTariffs());*/

        TariffsDomBuilder domBuilder=new TariffsDomBuilder();
        domBuilder.buildSetTariffs("src/main/resources/xmlxsd/tariffs.xml");
        System.out.println(domBuilder.getTariffs());
    }
}
