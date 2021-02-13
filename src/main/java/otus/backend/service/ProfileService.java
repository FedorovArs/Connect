package otus.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.backend.entity.User;
import otus.backend.exception.NotFoundException;
import otus.backend.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    public User changeSubscription(User channel, User subscriber) {
        Set<User> subscribers = channel.getSubscribers();

        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        } else {
            subscribers.add(subscriber);
        }

        return userRepository.save(channel);
    }

    public User getUserById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(NotFoundException::new);
    }
}
