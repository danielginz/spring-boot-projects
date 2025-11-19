package il.kors.springstudents.controller;

import il.kors.springstudents.model.Student;
import il.kors.springstudents.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

    //@Autowired //instead of @Autowired we use @AllArgsConstructor
    private StudentService service;

    @GetMapping("/all-students")
    public List<Student> findAllStudent() {
        return service.findAllStudent();
    }

    @PostMapping("save_student")
    public String saveStudent(@RequestBody Student student) {
        service.saveStudent(student);
        return "Student successfully saved";
    }

    @GetMapping("/{email}")
    //public Student findByEmail(@PathVariable("email") String email) {
    public Student findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PutMapping("update_student")
    public Student updateStudent(@RequestBody Student student) {
        return service.updateStudent(student);
    }

    @DeleteMapping("delete_student/{email}")
    public void deleteStudent(@PathVariable String email) {
        service.deleteStudent(email);
    }


    //http://localhost:8080/api/v1/students
    //install json viewer to format json in browser
    //@RestController includes @ResponseBody that serialize data
    //@RestController contains @Controller that contains @Component therefore it's a bean
}
