package ink.educat.controllers;

import ink.educat.service.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseBody
    public String addUser() {
        userService.getUserByEmail("lllll");
        return "{\"Если вы это видете, значит Educat работает \":1}";
    }


}
