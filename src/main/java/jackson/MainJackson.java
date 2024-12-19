package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Root;
import model.Student;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainJackson {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //Вычитваем данные
        Root root = mapper.readValue(new File("file.json"), Root.class);
        System.out.println(root);

        //Записать данные
        Root rootToJson = new Root();
        rootToJson.setCount(1);
        Student student = new Student();
        student.setId(1);
        student.setName("Илья");
        student.setAge(18);
        rootToJson.setStudents(List.of(student));

        mapper.writeValue(new File("fileIlya.json"), rootToJson);

        //Преобразование в строку
        System.out.println(mapper.writeValueAsString(root));

        //Если нужно вычитать коллекцию
        List<Student> students = mapper.readValue(new File("example.json"), mapper.getTypeFactory().constructCollectionType(List.class, Student.class));
        System.out.println(students);
    }
}
