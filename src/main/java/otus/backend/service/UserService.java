package otus.backend.service;

import otus.backend.entity.User;

import java.security.Principal;

public interface UserService {

    User getUserById(Principal principal);
}
