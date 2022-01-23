package uz.center.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.center.appjparelationship.entity.Faculty;
import uz.center.appjparelationship.entity.Group;
import uz.center.appjparelationship.entity.University;
import uz.center.appjparelationship.payload.FacultyDto;
import uz.center.appjparelationship.payload.GroupDto;
import uz.center.appjparelationship.repository.FacultyRepository;
import uz.center.appjparelationship.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    //vazirlik uchun
    @GetMapping
    public List<Group> getGroups(){
        List<Group> groupList = groupRepository.findAll();
        return groupList;
    }

    //rektorat uchun
    @GetMapping("/byUniversityId/{univertityId}")
    public List<Group> getGroupsByUniverityId(@PathVariable Integer univertityId){
        List<Group> allByFaculty_university_id = groupRepository.findAllByFaculty_University_Id(univertityId);
        List<Group> groupByUniversityId = groupRepository.getGroupByUniversityId(univertityId);
        List<Group> groupByUniversityIdNative = groupRepository.getGroupByUniversityIdNative(univertityId);

        return allByFaculty_university_id;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto){
        Group group = new Group();
        group.setName(groupDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()){
            return "This faculty not found";
        }
        group.setFaculty(optionalFaculty.get());

        groupRepository.save(group);
        return "Group added";
    }


}
