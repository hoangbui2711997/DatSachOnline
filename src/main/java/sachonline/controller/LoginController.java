package sachonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class LoginController {

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(String username, String password) {
//        return "login";
//    }
//

    /**
     * lấy form login
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }


    /**
     * hoàn tất form đăng ký
     * @return
     */
    @RequestMapping(value ="/register", method = RequestMethod.POST)
    public String register() {
        return "register";
    }

    /**
     * lấy form đăng ký
     * @return
     */
    @RequestMapping(value = "/register")
    public String getRegister() {
        return "register";
    }
}