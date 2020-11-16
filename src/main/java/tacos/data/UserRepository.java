package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.entities.security.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

}
