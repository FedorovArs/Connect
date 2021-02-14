package otus.backend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import otus.backend.entity.User;
import otus.backend.model.Views;
import otus.backend.service.ProfileService;
import otus.backend.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    @GetMapping("/user/all")
    @JsonView(Views.IdName.class)
    public List<User> list() {
        return profileService.getAllAuthorizedUsers();
    }

    @GetMapping("/profile/{id}")
    public User get(@PathVariable("id") String userId) {
        return profileService.getUserById(userId);
    }

    @PostMapping("/profile/{channelId}")
    public User changeSubscription(
            Principal principal,
            @PathVariable("channelId") User user
    ) {
        User subscriber = userService.getUserById(principal);
        if (!subscriber.equals(user)) {
            return profileService.changeSubscription(user, subscriber);
        }
        return user;
    }
}
