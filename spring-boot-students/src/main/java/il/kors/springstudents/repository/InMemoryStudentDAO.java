package il.kors.springstudents.repository;

import il.kors.springstudents.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryStudentDAO {
    private final List<Student> STUDENTS = new ArrayList<>();

    public List<Student> findAllStudent() {
        /*return List.of(
              Student.builder().firstName("Avi").email("avi@gmail.com").age(41).build(),
              Student.builder().firstName("Yosi").email("yosi@gmail.com").age(41).build(),
              Student.builder().firstName("Shlomi").email("shlomi@gmail.com").age(41).build()
        );*/

        return STUDENTS;
    }

    public Student saveStudent(Student student) {
        STUDENTS.add(student);
        return student;
    }

    public Student findByEmail(String email) {
        return STUDENTS.stream()
                .filter(element -> element.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public Student updateStudent(Student student) {
        var studentIndex = IntStream.range(0, STUDENTS.size())
                .filter(index -> STUDENTS.get(index).getEmail().equals(student.getEmail()))
                .findFirst()
                .orElse(-1);
        if (studentIndex > -1) {
            STUDENTS.set(studentIndex, student);
            return student;
        }
        return null;
    }

    public void deleteStudent(String email) {
        var student = findByEmail(email);
        if (student != null) {
            STUDENTS.remove(student);
        }
    }
}

//@InMemoryStudentDAO contains @Componentbtherefore it's a bean
