package fmi.cloudcomputing.buyerservice.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Spring Annotation that tells Spring that this class will fetch Users from the database
@Repository
//always extend JpaRepository because it has the basic CRUD operations
//it is a parametrized class and expects <ClassName, IdClassName>
//in this case, the `User` entity will have long(numeric) auto-generated Ids.
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
