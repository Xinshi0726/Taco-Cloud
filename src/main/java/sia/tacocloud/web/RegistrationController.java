package sia.tacocloud.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.sql.UserInfoRepository;
import sia.tacocloud.tacos.security.RegistrationForm;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserInfoRepository userRepo;

    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserInfoRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form)
    {
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
