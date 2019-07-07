package ink.educat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {


    @RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseBody
    public String addUser() {

        return "{\"Если вы это видете, значит Educat работает \":1}";
    }


}
