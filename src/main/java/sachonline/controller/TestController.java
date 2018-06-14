package sachonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Test controller - dùng để test
 */
@Controller
@RequestMapping("/home")
public class TestController {

    @RequestMapping(value = "/main")
    public String home() {
        return "main";
    }

//    @RequestMapping(value = "/test")
//    public String test() {
//        return "abc.html";
//    }
}
