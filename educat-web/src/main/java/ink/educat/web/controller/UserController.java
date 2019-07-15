package ink.educat.web.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import ink.educat.user.dao.api.entities.User;
import ink.educat.user.dao.api.entities.UserRole;
import ink.educat.user.dao.api.entities.UserStatus;
import ink.educat.user.dao.impl.UserDaoImpl;
import ink.educat.user.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
<<<<<<< Updated upstream
import java.time.LocalDateTime;
=======
import javax.servlet.http.HttpServletResponse;
>>>>>>> Stashed changes

@Controller
public class UserController {

    private final UserService userService;
    //private final TokenCreationService tokenCreationService;

    @Autowired
    public UserController(
            final UserService userService
//            final TokenCreationService tokenCreationService
    ) {
        this.userService = userService;
       // this.tokenCreationService = tokenCreationService;
    }
    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    @ResponseBody
<<<<<<< Updated upstream
    String signInPost(final HttpServletRequest request) {
=======
    void get(final HttpServletRequest request, final HttpServletResponse response) {
        singInPost(request, response);
    }

    void singInPost(final HttpServletRequest request, final HttpServletResponse response) {
>>>>>>> Stashed changes
        try {
            final String email = request.getParameter("email");
            final User user = userService.findUserByEmail(email);
            if (user.getPassword().equals(request.getParameter("password"))) {
                //response.addHeader("Authenticate", "Bareer " + tokenCreationService.getToken(user));

                Algorithm algorithm = Algorithm.HMAC256("secret");
                String token = JWT.create()
                        .withClaim("email", user.getEmail())
                        .withClaim("role", user.getUserRole().getCode())
                        .sign(algorithm);
                response.addHeader("Authorization", "Bearer " + token);
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    @ResponseBody
    String signUpPost(final HttpServletRequest request) {
        try {
            final User user = userService.findUserByEmail(request.getParameter("email"));
            return "Пользователь с таким email уже зарегестрирован!";
        } catch (final Exception ex) {
            User user = new User(
                    0 ,
                    request.getParameter("email"),
                    false,
                    request.getParameter("password"),
                    request.getParameter("first-name"),
                    request.getParameter("last-name"),
                    UserStatus.ACTIVE,
                    UserRole.USER,
                    LocalDateTime.now());

            try {
                userService.registrationUser(user);
                return "Пользователь успешно зарегестрировался!";
            } catch (final Exception ex2) {
                ex2.printStackTrace();
                return ex2.getMessage();
            }
        }
    }
}
