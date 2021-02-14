package otus.backend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import otus.backend.entity.User;
import otus.backend.model.Views;
import otus.backend.service.ProfileService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

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
            @AuthenticationPrincipal User subscriber,
            @PathVariable("channelId") User channel
    ) {
        if (subscriber.equals(channel)) {
            return channel;
        } else {
            return profileService.changeSubscription(channel, subscriber);
        }
    }
}
