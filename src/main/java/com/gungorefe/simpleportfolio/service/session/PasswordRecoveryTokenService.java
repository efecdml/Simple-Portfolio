package com.gungorefe.simpleportfolio.service.session;

import com.gungorefe.simpleportfolio.dto.EmailDto;
import com.gungorefe.simpleportfolio.entity.user.PasswordRecoveryToken;
import com.gungorefe.simpleportfolio.entity.user.User;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.user.PasswordRecoveryTokenRepository;
import com.gungorefe.simpleportfolio.service.EmailService;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PasswordRecoveryTokenService {
    private final PasswordRecoveryTokenRepository repository;
    private final EmailService emailService;
    private final Environment environment;

    /* Gets existing token or creates new one. If the token has already been sent, throws
     * PasswordRecoveryEmailAlreadySentException with the message according to the locale. If it has expired, removes
     * token from db and replaces with new one. At last, sends and saves the current or newly created token. */
    public PasswordRecoveryToken createAndSendEmail(
            User user,
            String localeName
    ) {
        PasswordRecoveryToken token = repository.findByUser_Email(user.getEmail()).orElse(create(user));
        boolean expired = isExpired(token);
        PasswordRecoveryToken newToken;

        if (token.isSent()) {
            Duration timeLeft = Duration.between(token.getExpirationDate(), LocalDateTime.now());

            throw ExceptionFactory.getPasswordRecoveryEmailAlreadySentException(
                    user.getEmail(),
                    timeLeft
            );
        }
        if (expired) {
            repository.delete(token);
            newToken = create(user);
        } else {
            newToken = token;
        }

        sendEmail(
                localeName,
                newToken
        );
        newToken.setSent(true);

        return repository.save(newToken);
    }

    public PasswordRecoveryToken validate(String token) {
        PasswordRecoveryToken recoveryToken = repository.findByToken(token).orElseThrow(() -> ExceptionFactory
                .getInvalidPasswordRecoveryTokenException(token));

        if (isExpired(recoveryToken)) {
            throw ExceptionFactory.getExpiredPasswordRecoveryTokenException(token);
        }

        return recoveryToken;
    }

    private PasswordRecoveryToken create(User user) {
        return new PasswordRecoveryToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusMinutes(5),
                false,
                user
        );
    }

    private boolean isExpired(PasswordRecoveryToken token) {
        return token.getExpirationDate().isBefore(LocalDateTime.now());
    }

    private void sendEmail(
            String localeName,
            PasswordRecoveryToken token
    ) {
        String url = String.join(
                "/",
                environment.getProperty("server.url"),
                "api/session/password-recovery/token",
                token.getToken()
        );
        String subject = localeName != null && localeName.equals(LocaleName.TURKISH.value)
                ? "Şifre kurtarma linki."
                : "Password recovery link";
        EmailDto dto = new EmailDto(
                token.getUser().getEmail(),
                subject,
                url
        );

        emailService.send(dto);
    }
}
