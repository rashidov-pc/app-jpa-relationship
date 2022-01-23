package uz.center.appjparelationship.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.center.appjparelationship.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findAllByGroup_Faculty_University_Id(Integer university_id, Pageable pageable);
}
