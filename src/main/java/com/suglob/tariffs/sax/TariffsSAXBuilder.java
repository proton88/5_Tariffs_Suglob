package com.suglob.tariffs.sax;

import com.suglob.tariffs.entityjaxb.Tariff;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class TariffsSAXBuilder {

    public static final Logger LOGGER = LogManager.getLogger(TariffsSAXBuilder.class);

    private Set<Tariff> tariffs;
    private TariffHandler th;
    private XMLReader reader;
    public TariffsSAXBuilder() {
        th = new TariffHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(th);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "Sax Parser error",e);
        }
    }
    public Set<Tariff> getTariffs() {
        return tariffs;
    }
    public void buildSetStudents(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "SAX parser error", e);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "IO Thread error", e);
        }
        tariffs = th.getTariffs();
    }
}
