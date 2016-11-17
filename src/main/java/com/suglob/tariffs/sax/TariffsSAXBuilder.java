package com.suglob.tariffs.sax;

import jaxb.Tariff;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class TariffsSAXBuilder {
    private Set<Tariff> tariffs;
    private TariffHandler th;
    private XMLReader reader;
    public TariffsSAXBuilder() {
        th = new TariffHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(th);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }
    public Set<Tariff> getTariffs() {
        return tariffs;
    }
    public void buildSetStudents(String fileName) {
        try {
// разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        tariffs = th.getTariffs();
    }
}
