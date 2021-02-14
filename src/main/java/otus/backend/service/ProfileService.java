package otus.backend.service;

import otus.backend.entity.User;

import java.util.List;

public interface ProfileService {
    List<User> getAllAuthorizedUsers();

    User getUserById(String userId);

    User changeSubscription(User channel, User subscriber);
}
