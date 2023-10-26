package StudentEx;

import java.util.HashMap;
import java.util.Map;

public class StudentRepository {
    private final Map<String, Student> studentRepository;

    public StudentRepository() {
        this.studentRepository = new HashMap<>();
    }

    public void create(Student student) {
        studentRepository.putIfAbsent(student.getName(),student);
    }

    public Student get(String name) {
        return studentRepository.get(name);
    }
}
