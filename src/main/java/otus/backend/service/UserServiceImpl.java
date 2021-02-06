package otus.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.backend.entity.User;
import otus.backend.repository.UserRepository;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    @Override
    public User getUserById(Principal principal) {
        if (principal == null) {
            return null;
        } else {
            return userRepository.findById(principal.getName()).orElse(null);
        }
    }
}
