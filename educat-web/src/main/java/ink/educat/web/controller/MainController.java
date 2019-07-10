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
//        try {
//            User user = userService.getUserByEmail("andreybrsv@yandex.ru");
//            return user.toString();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ex.getMessage();
//        }
        //return "{\"Если вы это видете, значит Educat работает \":1}";
        return "index";
    }


}
