package org.example.springbootrest_2_2.service;

import org.example.springbootrest_2_2.exeption.InvalidCredentials;
import org.example.springbootrest_2_2.exeption.UnauthorizedUser;
import org.example.springbootrest_2_2.model.Authorities;
import org.example.springbootrest_2_2.model.User;
import org.example.springbootrest_2_2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(User request) {
        String user = request.getUser();
        String password = request.getPassword();

        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
