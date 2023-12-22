package com.gungorefe.simpleportfolio.service.session;

import com.gungorefe.simpleportfolio.dto.session.LoginRequest;
import com.gungorefe.simpleportfolio.dto.session.LoginResponse;
import com.gungorefe.simpleportfolio.dto.session.PasswordDto;
import com.gungorefe.simpleportfolio.entity.user.PasswordRecoveryToken;
import com.gungorefe.simpleportfolio.entity.user.User;
import com.gungorefe.simpleportfolio.service.security.JwtService;
import com.gungorefe.simpleportfolio.service.security.SecurityService;
import com.gungorefe.simpleportfolio.util.WebUtils;
import com.gungorefe.simpleportfolio.vo.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SessionService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final WebUtils webUtils;
    private final SecurityService securityService;
    private final PasswordRecoveryTokenService passwordRecoveryTokenService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        ));

        User user = securityService.getUser(
                true,
                request.email()
        );
        String accessToken = jwtService.generateToken(
                Map.of("roles", user.getRole().getName()),
                user
        );
        ResponseCookie cookie = webUtils.createCookie(
                JwtToken.ACCESS_TOKEN.value,
                accessToken,
                Duration.ofDays(30)
        );
        LoginResponse response = new LoginResponse(
                cookie,
                user.getRole().getName()
        );

        return response;
    }

    public ResponseCookie logout() {
        return webUtils.deleteCookie(JwtToken.ACCESS_TOKEN.value);
    }

    public void createPasswordRecoveryToken(
            String username,
            String localeName
    ) {
        User user = securityService.getUser(
                true,
                username
        );

        passwordRecoveryTokenService.createAndSendEmail(
                user,
                localeName
        );
    }

    public void recoverPassword(
            String token,
            PasswordDto dto
    ) {
        securityService.validateAndMatchPasswords(
                dto.password(),
                dto.confirmedPassword()
        );

        PasswordRecoveryToken recoveryToken = passwordRecoveryTokenService.validate(token);
        User user = recoveryToken.getUser();
        user.setPassword(passwordEncoder.encode(dto.password()));

        securityService.saveUser(user);
    }
}
