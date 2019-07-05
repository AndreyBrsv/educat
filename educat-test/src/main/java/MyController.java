import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handle(Model model) {
        System.out.println("Я тут");
        model.addAttribute("message", "Hello World!");
        return "index";
    }
}
