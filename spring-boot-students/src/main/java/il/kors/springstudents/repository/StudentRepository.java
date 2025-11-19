package il.kors.springstudents.repository;

import il.kors.springstudents.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //customized methods, additional to automatic CRUD methods

    //we have automatic CRUD method void deleteByEmail(Student student);
    void deleteByEmail(String email);

    Student findStudentByEmail(String email);
}