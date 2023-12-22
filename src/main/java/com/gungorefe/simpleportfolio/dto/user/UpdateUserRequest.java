package com.gungorefe.simpleportfolio.dto.user;

public record UpdateUserRequest(
        String currentEmail,
        String newEmail,
        String password,
        String confirmedPassword,
        String role
) {
}
