package ink.educat.web.security.jwt;

import ink.educat.user.dao.api.entities.User;
import ink.educat.user.dao.api.entities.UserRole;
import ink.educat.user.dao.api.entities.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUserStatus().equals(UserStatus.ACTIVE),
                null,
                mapToGrantedAuthorities(Collections.singletonList(user.getUserRole()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<UserRole> userRoles) {
        return userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getJsonValue())).collect(Collectors.toList());
    }
}
