package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.data.UserRepository;
import tacos.entities.security.RegistrationFormUser;
import tacos.entities.security.User;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute(name = "registrationFormUser")
    public RegistrationFormUser registrationFormUser() {
        return new RegistrationFormUser();
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("registrationFormUser", registrationFormUser());
        return "register";
    }

    @PostMapping
    public String processRegistrationForm(@Valid RegistrationFormUser registrationFormUser, Errors errors, Model model) {
        // validation
        if (errors.hasErrors()) {
            log.info("Validation errors:\n" + errors.getFieldErrors().stream().map(error -> error.getField()+ ": " + error.getDefaultMessage())
                    .collect(Collectors.joining("\n")));
            model.addAttribute("fields", errors);
            return "register";
        }
        User encodedPasswordUser = encodePasswordAndReturnUser(registrationFormUser);
        userRepository.save(encodedPasswordUser);
        return "redirect:/login";
    }

    private User encodePasswordAndReturnUser(RegistrationFormUser user) {
        String plainPassword = user.getPassword();
        return new User(user.getUsername(),
                passwordEncoder.encode(plainPassword),
                user.getFullName(),
                user.getStreet(),
                user.getCity(),
                user.getZipCode(),
                user.getPhoneNumber(),
                true,
                true,
                true,
                true);
    }
}
