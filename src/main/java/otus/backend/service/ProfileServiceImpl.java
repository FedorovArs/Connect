package otus.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.backend.entity.User;
import otus.backend.exception.NotFoundException;
import otus.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    public User changeSubscription(User channel, User subscriber) {
        Set<User> subscriptions = subscriber.getSubscriptions();

        if (subscriptions.contains(channel)) {
            subscriptions.remove(channel);
        } else {
            subscriptions.add(channel);
        }

        return userRepository.save(subscriber);
    }

    public User getUserById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(NotFoundException::new);
    }

    public List<User> getAllAuthorizedUsers() {
        return userRepository.findAll();
    }
}
