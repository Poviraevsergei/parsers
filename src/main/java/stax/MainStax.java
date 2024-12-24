package stax;

import model.Root;
import model.Student;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainStax {
    public static void main(String[] args) {
        Root root = null;
        List<Student> students = null;
        Student student = null;

        try (FileInputStream fileInputStream = new FileInputStream("file.xml")) {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = inputFactory.createXMLEventReader(fileInputStream);

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (event.isStartElement()) {
                    String startElement = event.asStartElement().getName().getLocalPart();
                    switch (startElement) {
                        case "root" -> root = new Root();
                        case "count" -> {
                            event = reader.nextEvent();
                            if (root != null) {
                                root.setCount(Integer.parseInt(event.asCharacters().getData()));
                            }
                        }
                        case "students" -> students = new ArrayList<>();
                        case "student" -> {
                            student = new Student();
                            student.setId(Integer.parseInt(event.asStartElement().getAttributes().next().getValue()));
                        }
                        case "name" -> {
                            event = reader.nextEvent();
                            if (student != null) {
                                student.setName(event.asCharacters().getData());
                            }
                        }
                        case "age" -> {
                            event = reader.nextEvent();
                            if (student != null) {
                                student.setAge(Integer.parseInt(event.asCharacters().getData()));
                            }
                        }
                    }
                }

                if (event.isEndElement()) {
                    String endElement = event.asEndElement().getName().getLocalPart();
                    switch (endElement) {
                        case "student" -> {
                            if (student != null && students != null) {
                                students.add(student);
                            }
                        }
                        case "root" -> {
                            if (root != null) {
                                root.setStudents(students);
                            }
                        }
                    }
                }
            }

            if (root != null) {
                System.out.println(root);
            }
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
