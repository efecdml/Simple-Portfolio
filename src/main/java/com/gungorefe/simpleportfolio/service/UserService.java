package com.gungorefe.simpleportfolio.service;

import com.gungorefe.simpleportfolio.dto.converter.user.UserDtoConverter;
import com.gungorefe.simpleportfolio.dto.user.CreateUserRequest;
import com.gungorefe.simpleportfolio.dto.user.UpdateUserRequest;
import com.gungorefe.simpleportfolio.dto.user.UserDto;
import com.gungorefe.simpleportfolio.entity.user.Role;
import com.gungorefe.simpleportfolio.entity.user.User;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.service.security.JwtService;
import com.gungorefe.simpleportfolio.service.security.SecurityService;
import com.gungorefe.simpleportfolio.util.WebUtils;
import com.gungorefe.simpleportfolio.vo.JwtToken;
import com.gungorefe.simpleportfolio.vo.RegexPattern;
import com.gungorefe.simpleportfolio.vo.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService {
    private final JwtService jwtService;
    private final WebUtils webUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoConverter dtoConverter;
    private final SecurityService securityService;

    public User create(CreateUserRequest request) {
        validateEmail(request.email());
        securityService.validateAndMatchPasswords(
                request.password(),
                request.confirmedPassword()
        );

        Role role = securityService.getRole(request.role());
        User user = dtoConverter.convertToUser(
                request.email(),
                passwordEncoder.encode(request.password()),
                role
        );

        return securityService.saveUser(user);
    }

    public UserDto getDto() {
        String accessToken = webUtils.getCookieValue(JwtToken.ACCESS_TOKEN.value);
        String email = jwtService.extractUsername(accessToken);
        User user = securityService.getUser(
                false,
                email
        );

        return dtoConverter.convertToUserDto(user);
    }

    public Set<UserDto> getAllDtos() {
        return dtoConverter.convertToUserDtos(securityService.getAllUsers());
    }

    /* Checks new email is not in use, throws EmailAlreadyInUseException if in use. Gets User from "updateAsAdmin" or
     * "updateAsMod" depending on whether the role is ROLE_ADMIN or ROLE_MOD. Because administrator users can change
     *  other users' information but moderator users can not. */
    public User update(UpdateUserRequest request) {
        validateEmail(request.newEmail());

        if (!request.currentEmail().equals(request.newEmail()) &&
                securityService.userExists(request.newEmail())) {
            throw ExceptionFactory.getEmailAlreadyInUseException(request.newEmail());
        }

        String accessToken = webUtils.getCookieValue(JwtToken.ACCESS_TOKEN.value);
        String roleName = jwtService.extractRoles(accessToken);
        User user = roleName.equals(RoleName.ROLE_ADMIN.name())
                ? updateAsAdmin(request)
                : updateAsMod(accessToken, request);

        return securityService.saveUser(user);
    }

    /* Assigns new password as encrypted if new one is given, uses default password if not, by "getPasswordUponRequest".
     * Same process happens for assigning a role. Then returns User from the dto converter with changes applied. */
    private User updateAsAdmin(UpdateUserRequest request) {
        User user = securityService.getUser(
                false,
                request.currentEmail()
        );
        String password = getPasswordUponRequest(
                user,
                request
        );
        Role role = user.getRole().getName().equals(request.role())
                ? user.getRole()
                : securityService.getRole(request.role());

        return dtoConverter.convertToUser(
                user.getId(),
                request.newEmail(),
                password,
                role
        );
    }

    /* Gets email from the access token and checks if the email is same as the given email. Throws UnauthorizedException
     * if these emails are not same. Assigns new password as encrypted if new one is given, uses default password if not,
     * by "getPasswordUponRequest". Then returns User from the dto converter with changes applied. */
    private User updateAsMod(
            String accessToken,
            UpdateUserRequest request
    ) {
        String email = jwtService.extractUsername(accessToken);
        User user = securityService.getUser(
                false,
                email
        );
        String password;

        if (!email.equals(request.currentEmail())) {
            throw ExceptionFactory.getUnauthorizedException(email);
        }

        password = getPasswordUponRequest(
                user,
                request
        );

        return dtoConverter.convertToUser(
                user.getId(),
                request.newEmail(),
                password,
                user.getRole()
        );
    }

    public void delete(String email) {
        securityService.delete(email);
    }

    private void validateEmail(String email) {
        boolean regexMatches = RegexPattern.match(
                email,
                RegexPattern.EMAIL
        );

        if (!regexMatches) {
            throw ExceptionFactory.getUnacceptableEmailException(email);
        }
    }

    /* Returns current password if password is null or empty. If not, matches passwords then returns as encrypted. */
    private String getPasswordUponRequest(
            User user,
            UpdateUserRequest request
    ) {
        String password = request.password();

        if (password == null ||
                password.isEmpty()) {
            return user.getPassword();
        } else {
            securityService.validateAndMatchPasswords(
                    request.password(),
                    request.confirmedPassword()
            );

            return passwordEncoder.encode(request.password());
        }
    }
}
