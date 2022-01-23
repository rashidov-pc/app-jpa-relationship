package uz.center.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.center.appjparelationship.entity.Address;
import uz.center.appjparelationship.entity.University;
import uz.center.appjparelationship.payload.UniversityDTO;
import uz.center.appjparelationship.repository.AddressRepository;
import uz.center.appjparelationship.repository.Universityrepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    Universityrepository universityrepository;

    @Autowired
    AddressRepository addressRepository;

    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities(){
        List<University> universityList = universityrepository.findAll();
        return universityList;
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDTO universityDTO){
        Address address = new Address();
        address.setRegion(universityDTO.getRegion());
        address.setDistrict(universityDTO.getDistrict());
        address.setStreet(universityDTO.getStreet());
        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDTO.getName());
        university.setAddress(savedAddress);

        universityrepository.save(university);

        return "University added";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDTO universityDTO){
        Optional<University> optionalUniversity = universityrepository.findById(id);
        if (optionalUniversity.isPresent()){
            University university = optionalUniversity.get();
            university.setName(universityDTO.getName());

            Address address = university.getAddress();
            address.setRegion(universityDTO.getRegion());
            address.setDistrict(universityDTO.getDistrict());
            address.setStreet(universityDTO.getStreet());
            addressRepository.save(address);

            universityrepository.save(university);

            return "Edited university";
        }else {
            return "Not found university";
        }
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id){
        universityrepository.deleteById(id);

        return "Deleted university";
    }
}
