package com.gungorefe.simpleportfolio.dto.converter.user;

import com.gungorefe.simpleportfolio.dto.user.UserDto;
import com.gungorefe.simpleportfolio.entity.user.Role;
import com.gungorefe.simpleportfolio.entity.user.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {
    public User convertToUser(
            String email,
            String password,
            Role role
    ) {
        User user = new User(
                email,
                password,
                role
        );

        return user;
    }

    public User convertToUser(
            String id,
            String email,
            String password,
            Role role
    ) {
        User user = new User(
                id,
                email,
                password,
                role
        );

        return user;
    }

    public UserDto convertToUserDto(User user) {
        UserDto dto = new UserDto(
                user.getEmail(),
                user.getRole().getName()
        );

        return dto;
    }

    public Set<UserDto> convertToUserDtos(Collection<User> users) {
        Set<UserDto> dtos = users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toSet());

        return dtos;
    }
}
