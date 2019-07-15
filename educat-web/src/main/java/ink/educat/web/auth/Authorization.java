package ink.educat.web.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class Authorization {

    public static void main(String[] args) {
        JWT jwt = new JWT();

        String token = "Some_token";


        Algorithm algorithm = Algorithm.HMAC256("secret");

        HashMap<String, Object> header = new HashMap<>();

         token = JWT.create()
                 .withHeader(header)
                 .withClaim("role", "admin")
                 .sign(algorithm);

        DecodedJWT decodedJWT = jwt.decodeJwt(token);
        System.out.println("Token " + token);

        // Можем построить верификацию на основе алгоритма
        Verification verification = JWT.require(algorithm);
        verification.withClaim("role", "admin")
                .withClaim("role", "tutor");
        JWTVerifier verifier = verification.build();
        DecodedJWT decodedJWT1 = verifier.verify(token);
        // or
        verifier.verify(decodedJWT);

        System.out.println(decodedJWT1.getPayload());


    }
}
