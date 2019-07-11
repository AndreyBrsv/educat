package ink.educat.web.controller;

import ink.educat.user.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value="/", method=RequestMethod.GET)
    //@ResponseBody
    public String addUser() {
        return "index.jsp";
    }

    @RequestMapping(value="/sign-in", method=RequestMethod.GET)
    public String signIn() {
        return "sign-in.jsp";
    }

    @RequestMapping(value="/sign-up", method=RequestMethod.GET)
    public String signUp() {
        return "sign-up.jsp";
    }


}
