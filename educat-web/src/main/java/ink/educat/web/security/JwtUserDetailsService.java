package ink.educat.web.security;

import ink.educat.user.dao.api.UserNotFoundException;
import ink.educat.user.dao.api.entities.User;
import ink.educat.user.service.api.UserService;
import ink.educat.web.security.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) { this.userService = userService; }

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        User user = userService.findUserByEmail(email);

        return JwtUserFactory.create(user);
    }
}
