package otus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import otus.backend.repository.MessageRepository;
import otus.backend.service.UserService;

import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final MessageRepository messageRepo;
    private final UserService userService;

    @GetMapping
    public String main(Model model, Principal principal) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", userService.getUserById(principal));
        data.put("messages", messageRepo.findAll());

        model.addAttribute("frontendData", data);

        return "index";
    }
}
