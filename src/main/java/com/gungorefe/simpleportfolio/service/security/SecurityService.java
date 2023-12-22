package com.gungorefe.simpleportfolio.service.security;

import com.gungorefe.simpleportfolio.entity.user.User;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService {
    private final UserRepository userRepository;

    public User getUser(String username) {
        User user = userRepository.findByEmailWithRole(username).orElseThrow(ExceptionFactory::getBadCredentialsException);

        return user;
    }
}
