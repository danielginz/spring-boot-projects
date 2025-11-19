package il.kors.springstudents.service.impl;

import il.kors.springstudents.model.Student;
import il.kors.springstudents.repository.StudentRepository;
import il.kors.springstudents.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary//StudentServiceImpl is preferred Service for Spring,
        //if StudentServiceImpl for any reason will stop working
        //InMemoryStidentServiceImply will replace it.
        // @Primary alternative for @Qualifier
public class StudentServiceImpl implements StudentService {
    //@Autowired
    private final StudentRepository repository;

    @Override
    public List<Student> findAllStudent() {
        return repository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Student findByEmail(String email) {
        return repository.findStudentByEmail(email);
    }

    @Override
    public Student updateStudent(Student student) {
        return repository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(String email) {
        repository.deleteByEmail(email);
    }
}