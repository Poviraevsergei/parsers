package JAXB;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.Root;
import model.Student;

import java.io.File;
import java.util.List;

public class MainJaxb {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Root.class);

        //Читаем из XML
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Root root = (Root) unmarshaller.unmarshal(new File("file.xml"));
        System.out.println(root);

        //Записать в файл
        Root rootToXml = new Root();
        rootToXml.setCount(1);
        Student student = new Student();
        student.setId(1);
        student.setName("Илья");
        student.setAge(18);
        rootToXml.setStudents(List.of(student));

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(rootToXml, new File("ilya.xml"));
    }
}
