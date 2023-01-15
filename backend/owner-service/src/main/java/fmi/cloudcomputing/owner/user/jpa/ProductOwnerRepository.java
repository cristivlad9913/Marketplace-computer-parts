package fmi.cloudcomputing.owner.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductOwnerRepository extends JpaRepository<ProductOwner, Long> {
   Optional<ProductOwner> findByUsername(String username);
   Optional<ProductOwner> findByUsernameAndPassword(String username, String password);
}
