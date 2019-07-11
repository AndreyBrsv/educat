package ink.educat.web.controller;

import ink.educat.user.dao.api.entities.ShortDetailedUser;
import ink.educat.user.dao.api.entities.User;
import ink.educat.user.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    @ResponseBody
    String singInPost(final HttpServletRequest request) {
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

}
