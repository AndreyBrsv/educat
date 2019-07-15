package ink.educat.web.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import ink.educat.security.service.api.SecurityService;
import ink.educat.user.dao.api.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenCreationService {

    private final Algorithm algorithm;

    @Autowired
    public TokenCreationService(final SecurityService securityService) {
        this.algorithm = Algorithm.HMAC256(
                securityService.getValueFor("EDUCAT_JWT_SECRET")
        );
    }

    public String getToken(final User user) {
        return JWT.create()
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getUserRole().getCode())
                .sign(algorithm);
    }
}
