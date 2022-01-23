package uz.center.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.center.appjparelationship.entity.Student;
import uz.center.appjparelationship.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    //1.Vazirlik
    @GetMapping("/forMinistry")
    public Page<Student> getStudentsForMinistry(@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }
    //2.Universitet
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentsForUniversity(@PathVariable Integer universityId,
                                                  @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_University_Id(universityId, pageable);
        return studentPage;
    }
    //3.Fakultetni dekanati
    //4.Guruh rahbari
}
