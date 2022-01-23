package uz.center.appjparelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.center.appjparelationship.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);
}
