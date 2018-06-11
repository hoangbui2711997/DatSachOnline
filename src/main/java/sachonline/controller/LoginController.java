package sachonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(String username, String password) {
//        return "login";
//    }
//

    @RequestMapping(value ="/register", method = RequestMethod.POST)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register")
    public String getRegister() {
        return "register";
    }
}