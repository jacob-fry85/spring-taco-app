package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.User;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsernam(String username);
}
