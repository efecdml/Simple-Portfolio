package com.gungorefe.simpleportfolio;

import com.gungorefe.simpleportfolio.entity.user.PasswordRecoveryToken;
import com.gungorefe.simpleportfolio.entity.user.User;
import com.gungorefe.simpleportfolio.exception.session.PasswordRecoveryEmailAlreadySentException;
import com.gungorefe.simpleportfolio.repository.user.PasswordRecoveryTokenRepository;
import com.gungorefe.simpleportfolio.service.EmailService;
import com.gungorefe.simpleportfolio.service.session.PasswordRecoveryTokenService;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import com.gungorefe.simpleportfolio.vo.RoleName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PasswordRecoveryTokenServiceTest {
    @Mock
    private EmailService emailService;
    @Mock
    private Environment environment;
    @Mock
    private PasswordRecoveryTokenRepository repository;
    private final User user = new User("example@email.com", RoleName.ROLE_ADMIN.name());
    @InjectMocks
    private PasswordRecoveryTokenService passwordRecoveryTokenService;

    @Test
    public void givenUsernameAndLocaleNameForUnsentToken_shouldReturnPasswordRecoveryTokenDto() {
        PasswordRecoveryToken recoveryToken = new PasswordRecoveryToken("token", LocalDateTime.now().plusMinutes(5), false, user);
        when(repository.findByUser_Email(anyString())).thenReturn(Optional.of(recoveryToken));
        when(repository.save(any(PasswordRecoveryToken.class))).thenAnswer(i -> i.getArguments()[0]);

        PasswordRecoveryToken token = passwordRecoveryTokenService.createAndSendEmail(
                user,
                LocaleName.TURKISH.value
        );

        assertFalse(token.getToken().isEmpty());
        assertTrue(token.isSent());
    }

    @Test
    public void givenUsernameAndLocaleNameForSentToken_shouldThrowPasswordRecoveryEmailAlreadySentException() {
        PasswordRecoveryToken recoveryToken = new PasswordRecoveryToken("token", LocalDateTime.now().plusMinutes(5), true, user);
        when(repository.findByUser_Email(anyString())).thenReturn(Optional.of(recoveryToken));

        assertThrowsExactly(
                PasswordRecoveryEmailAlreadySentException.class,
                () -> passwordRecoveryTokenService.createAndSendEmail(
                        user,
                        LocaleName.TURKISH.value
                )
        );
    }
}
