package tacos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.thymeleaf.util.StringUtils;
import tacos.entities.security.User;

@Controller
public class HomeController {
    @GetMapping("/")
    public String mainPage() {
        return "home";
    }

    @GetMapping("/subpage")
    public String subPage() {
        return "subpage";
    }

    @GetMapping("/error")
    public String errorPage(@ModelAttribute Object error) {
        StringUtils.toString(error);
        return "";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
}
