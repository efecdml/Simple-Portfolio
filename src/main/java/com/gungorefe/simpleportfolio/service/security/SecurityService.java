package com.gungorefe.simpleportfolio.service.security;

import com.gungorefe.simpleportfolio.entity.user.Role;
import com.gungorefe.simpleportfolio.entity.user.User;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.user.RoleRepository;
import com.gungorefe.simpleportfolio.repository.user.UserRepository;
import com.gungorefe.simpleportfolio.vo.RegexPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class SecurityService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LoginAttemptService loginAttemptService;

    @CacheEvict(
            value = "users",
            key = "#user.email"
    )
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /* "secure" field is used to make client unaware of registered users by throwing BadCredentialsException instead
     * of UserNotFoundException.
     *
     * Checks if client is blocked for attempting Brute Force Authentication, if it is blocked throws
     * BruteForceAuthenticationAttemptException.
     *
     * At last returns the User object. */
    @Cacheable(
            value = "users",
            key = "#username"
    )
    public User getUser(
            boolean secure,
            String username
    ) {
        User user;

        if (secure) {
            if (loginAttemptService.isBlocked()) {
                throw ExceptionFactory.getBruteForceAuthenticationAttemptException();
            }

            user = userRepository.findByEmailWithRole(username).orElseThrow(ExceptionFactory::getBruteForceAuthenticationAttemptException);
        } else {
            user = userRepository.findByEmailWithRole(username).orElseThrow(() -> ExceptionFactory.getUserNotFoundException(username));
        }

        return user;
    }

    @Cacheable(
            value = "roles",
            key = "#roleName"
    )
    public Role getRole(String roleName) {
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> ExceptionFactory.getRoleNotFoundException(roleName));

        return role;
    }

    public Set<User> getAllUsers() {
        Set<User> users = userRepository.findEmailAndRoleNameOfAll();

        return users;
    }

    public boolean userExists(String username) {
        boolean exists = userRepository.existsByEmail(username);

        return exists;
    }

    @CacheEvict(
            value = "users",
            key = "#username"
    )
    public void delete(String username) {
        User user = userRepository.findIdByEmail(username).orElseThrow(() -> ExceptionFactory.getUserNotFoundException(username));

        userRepository.delete(user);
    }

    public void validateAndMatchPasswords(
            String password,
            String confirmedPassword
    ) {
        if (!password.equals(confirmedPassword)) {
            throw ExceptionFactory.getPasswordsDoNotMatchException();
        }

        boolean regexMatches = RegexPattern.match(
                password,
                RegexPattern.PASSWORD
        );

        if (!regexMatches) {
            throw ExceptionFactory.getUnacceptablePasswordException();
        }
    }
}
