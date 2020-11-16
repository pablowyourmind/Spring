package tacos.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Profile("local_test")  // available for local_test only
//@Profile("!local_test")  // not available for local_test
@RequestMapping("/subpage/test")
public class SubpageController {

    @GetMapping
    @ResponseBody
    public String test() {
        return "Test page for local_test profile only.";
    }
}
