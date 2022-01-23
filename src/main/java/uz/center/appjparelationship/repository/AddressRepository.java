package uz.center.appjparelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.center.appjparelationship.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
