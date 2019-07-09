package ink.educat.controllers;

import ink.educat.dao.user.api.Entities.ShortDetailedUser;
import ink.educat.service.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

}
