package tacos.auth.entity.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.auth.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
