package dom;

import model.Root;
import model.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//Java EE : javax(старые) -> jakarta(новые)
public class MainDom {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        //Создаем экземпляр фабрики парсера
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //Распарсит XML файл
        Document doc = factory.newDocumentBuilder().parse(new File("file.xml"));
        doc.getDocumentElement().normalize();

        //Создаем модели
        Root root = new Root();
        List<Student> students = new ArrayList<>();
        Student student = null;

        //Получаем поле Count
        NodeList allCountNodes = doc.getElementsByTagName("count");
        if (allCountNodes.getLength() == 1) {
            root.setCount(Integer.parseInt(allCountNodes.item(0).getTextContent()));
        }

        //Получаем список студентов
        NodeList studentList = doc.getElementsByTagName("student");
        if (studentList.getLength() == root.getCount()) {
            for (int i = 0; i < studentList.getLength(); i++) {
                Element studentElement = (Element)studentList.item(i);
                student = new Student();
                //Поле id
                student.setId(Integer.parseInt(studentElement.getAttribute("id")));
                //Поле name
                student.setName(studentElement.getElementsByTagName("name").item(0).getTextContent());
                //Поле age
                student.setAge(Integer.parseInt(studentElement.getElementsByTagName("age").item(0).getTextContent()));
                students.add(student);
            }
            root.setStudents(students);
        }

        //Печатаем результат
        System.out.println(root);
    }
}
