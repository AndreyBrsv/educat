package ink.educat.web.controller;

import ink.educat.user.dao.api.entities.ShortDetailedUser;
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
import java.time.LocalDateTime;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    @ResponseBody
    String signInPost(final HttpServletRequest request) {
        try {
            final User user = userService.findUserByEmail(request.getParameter("email"));
            if (user.getPassword().equals(request.getParameter("password"))) {
                final ShortDetailedUser shortDetailedUser = userService
                        .getShortDetailedUserById(user.getId());
                return shortDetailedUser.toString();
            } else {
                return "Try again";
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
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

            userService.registrationUser(user);
            return "Пользователь успешно зарегестрировался!";
        }
    }
}
