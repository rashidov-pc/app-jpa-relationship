package uz.center.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.center.appjparelationship.entity.Subject;
import uz.center.appjparelationship.repository.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    //create
    @PostMapping
    public String add(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName){
            return "This subject already exist";
        }
        subjectRepository.save(subject);
        return "Subject added";
    }

    //Read
    @GetMapping
    public List<Subject> getSubjects(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }


}
