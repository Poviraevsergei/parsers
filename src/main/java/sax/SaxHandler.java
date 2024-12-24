package sax;

import model.Root;
import model.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {
    private Root root = null;
    private List<Student> students = null;
    private Student student = null;

    private boolean isName = false;
    private boolean isAge = false;
    private boolean isCount = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("root")) {
            root = new Root();
        }
        if (qName.equals("count")) {
            isCount = true;
        }
        if (qName.equals("name")) {
            isName = true;
        }
        if (qName.equals("age")) {
            isAge = true;
        }
        if (qName.equals("students")) {
            students = new ArrayList<>();
        }
        if (qName.equals("student")) {
            student = new Student();
            student.setId(Integer.parseInt(attributes.getValue("id")));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isCount) {
            root.setCount(Integer.parseInt(String.valueOf(ch, start, length)));
            isCount = false;
        }
        if (isName) {
            student.setName(String.valueOf(ch, start, length));
            isName = false;
        }
        if (isAge) {
            student.setAge(Integer.parseInt(String.valueOf(ch, start, length)));
            isAge = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("student")) {
            students.add(student);
        }
        if (qName.equals("students")) {
            root.setStudents(students);
        }
    }

    public Root getRoot() {
        return root;
    }
}
