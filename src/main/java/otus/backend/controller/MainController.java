package otus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import otus.backend.entity.User;
import otus.backend.repository.MessageRepository;
import otus.backend.service.MessageService;
import otus.backend.service.UserService;

import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    @Value("${spring.profiles.active}")
    private String profile;

    //    private final MessageRepository messageRepo;
    private final UserService userService;
    private final MessageService messageService;

    @GetMapping
    public String main(Model model, Principal principal) {
        HashMap<Object, Object> data = new HashMap<>();

        User user = userService.getUserById(principal);
        if (user != null) {
            data.put("profile", user);
            data.put("messages", messageService.getUserMessagesAndSubscriptions(user));
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
