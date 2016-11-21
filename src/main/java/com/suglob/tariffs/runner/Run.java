package com.suglob.tariffs.runner;

import com.suglob.tariffs.dom.TariffsDomBuilder;
import com.suglob.tariffs.sax.TariffsSAXBuilder;
import com.suglob.tariffs.stax.TariffsStAXBuilder;


public class Run {
    public static final String FILE="src/main/resources/xmlxsd/tariffs.xml";

    public static void main(String[] args) {


        TariffsSAXBuilder saxBuilder=new TariffsSAXBuilder();
        saxBuilder.buildSetStudents(FILE);
        System.out.println();

        TariffsDomBuilder domBuilder=new TariffsDomBuilder();
        domBuilder.buildSetTariffs(FILE);
        System.out.println();

        TariffsStAXBuilder stAXBuilder=new TariffsStAXBuilder();
        stAXBuilder.buildSetTariffs(FILE);
        System.out.println();
    }
}
