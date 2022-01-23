package uz.center.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.center.appjparelationship.entity.Faculty;
import uz.center.appjparelationship.entity.University;
import uz.center.appjparelationship.payload.FacultyDto;
import uz.center.appjparelationship.repository.FacultyRepository;
import uz.center.appjparelationship.repository.Universityrepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    Universityrepository universityrepository;

    //vazirlik uchun
    @GetMapping
    public List<Faculty> getFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        return faculties;
    }

    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean existsByNameAndUniversityId = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (existsByNameAndUniversityId) {
            return "This faculty already exists";
        }
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityrepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent()) {
            return "This university not found";
        }
        faculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(faculty);
        return "Added faculty";
    }

    //universitet xodimi uchun
    @GetMapping("/byUniversityId/{univertityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer univertityId) {
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(univertityId);
        return allByUniversityId;

    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {
            facultyRepository.deleteById(id);
            return "Faculty deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }
    }

    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());
            Optional<University> optionalUniversity = universityrepository.findById(facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent()) {
                return "University not found";
            }
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty edited";
        }
        return "Faculty not found";
    }

}
