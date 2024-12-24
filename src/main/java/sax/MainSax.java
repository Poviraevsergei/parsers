package sax;

import model.Root;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class MainSax {
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        //Создание экземрляра фабрики
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        SaxHandler saxHandler = new SaxHandler();

        try {
            saxParser.parse(new File("file.xml"),saxHandler);
            Root root = saxHandler.getRoot();
            System.out.println(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
