package tacos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.data.UserRepository;
import tacos.entities.security.User;

/**
 * Implementation of Spring Security's UserDetailService, which is used to
 * authenticate users.
 * This implementation uses UserRepository class and custom User entity.
 *
 * NOTE: UserDetailsService should be specified in security configuration.
 * NOTE2: Add Primary annotation, because there is also InMemoryUserDetailsManager
 * provided by Spring Security.
 */
@Primary
@Service
public class TacoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Autowired used to inject the bean.
     * @param userRepository - Injected UserRepository bean.
     */
    @Autowired
    public TacoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User '" + username + "' not found!");
        }
    }
}
