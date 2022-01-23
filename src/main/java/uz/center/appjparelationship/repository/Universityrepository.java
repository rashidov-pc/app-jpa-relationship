package uz.center.appjparelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.center.appjparelationship.entity.University;

@Repository
public interface Universityrepository extends JpaRepository<University, Integer> {

}
