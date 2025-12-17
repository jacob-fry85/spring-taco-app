package tacos.data;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tacos.User;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
