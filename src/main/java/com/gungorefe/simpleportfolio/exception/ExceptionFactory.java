package com.gungorefe.simpleportfolio.exception;

import com.gungorefe.simpleportfolio.exception.image.ImageNotFoundException;
import com.gungorefe.simpleportfolio.exception.image.MalformedImageMimeTypeException;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageMimeTypeException;
import com.gungorefe.simpleportfolio.exception.image.UnacceptableImageNameException;
import com.gungorefe.simpleportfolio.exception.page.ComponentNotFoundException;
import com.gungorefe.simpleportfolio.exception.page.InvalidLocaleException;
import com.gungorefe.simpleportfolio.exception.page.PageNotFoundException;
import com.gungorefe.simpleportfolio.exception.security.BruteForceAuthenticationAttemptException;
import com.gungorefe.simpleportfolio.exception.security.RoleNotFoundException;
import com.gungorefe.simpleportfolio.exception.security.UnauthorizedException;
import com.gungorefe.simpleportfolio.exception.session.ExpiredPasswordRecoveryTokenException;
import com.gungorefe.simpleportfolio.exception.session.InvalidPasswordRecoveryTokenException;
import com.gungorefe.simpleportfolio.exception.session.PasswordRecoveryEmailAlreadySentException;
import com.gungorefe.simpleportfolio.exception.user.*;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.PageName;
import org.springframework.security.authentication.BadCredentialsException;

import java.text.MessageFormat;
import java.time.Duration;

public class ExceptionFactory {
    public static MalformedImageMimeTypeException getMalformedImageMimeTypeException(String mimeType) {
        return new MalformedImageMimeTypeException("Image could not be processed due to unsupported MIME type: " + mimeType);
    }

    public static UnacceptableImageMimeTypeException getUnacceptableImageMimeTypeException(String mimeType) {
        return new UnacceptableImageMimeTypeException("Unacceptable MIME type: " + mimeType);
    }

    public static UnacceptableImageNameException getUnacceptableImageNameException(String imageName) {
        return new UnacceptableImageNameException("Unacceptable image name: " + imageName);
    }

    public static InvalidLocaleException getInvalidLocaleException(String localeName) {
        return new InvalidLocaleException("Invalid locale: " + localeName);
    }

    public static PageNotFoundException getPageNotFoundException(PageName name) {
        return new PageNotFoundException("Page not found: " + name.value);
    }

    public static ComponentNotFoundException getComponentNotFoundException(
            ComponentName name,
            int id
    ) {
        return new ComponentNotFoundException(MessageFormat.format(
                "Page component named: {0} with ID: {1} could not found",
                name,
                id
        ));
    }

    public static ImageNotFoundException getImageNotFoundException(String imageName) {
        return new ImageNotFoundException("Image not found: " + imageName);
    }

    public static BadCredentialsException getBadCredentialsException() {
        return new BadCredentialsException("Bad credentials");
    }

    public static BruteForceAuthenticationAttemptException getBruteForceAuthenticationAttemptException() {
        return new BruteForceAuthenticationAttemptException("Login service is forbidden for this client for 24 hours.");
    }

    public static UserNotFoundException getUserNotFoundException(String email) {
        return new UserNotFoundException(MessageFormat.format(
                "User with email: {0} not found",
                email
        ));
    }

    public static RoleNotFoundException getRoleNotFoundException(String roleName) {
        return new RoleNotFoundException("Role not found: " + roleName);
    }

    public static UnacceptableEmailException getUnacceptableEmailException(String email) {
        return new UnacceptableEmailException("Unacceptable email: " + email);
    }

    public static PasswordsDoNotMatchException getPasswordsDoNotMatchException() {
        return new PasswordsDoNotMatchException("Passwords do not match");
    }

    public static UnacceptablePasswordException getUnacceptablePasswordException() {
        return new UnacceptablePasswordException("Unacceptable password, must include 6-12 length numbers and both lower and upper case letters");
    }

    public static EmailAlreadyInUseException getEmailAlreadyInUseException(String email) {
        return new EmailAlreadyInUseException("Email is already in use: " + email);
    }

    public static UnauthorizedException getUnauthorizedException(String email) {
        return new UnauthorizedException(MessageFormat.format(
                "User with email: {0} has no authorization to perform this action",
                email
        ));
    }

    public static PasswordRecoveryEmailAlreadySentException getPasswordRecoveryEmailAlreadySentException(
            String email,
            Duration timeLeft
    ) {
        return new PasswordRecoveryEmailAlreadySentException(MessageFormat.format(
                "Email for {0} is already sent. {1} minutes left to send again.",
                email,
                timeLeft.toMinutesPart() + ":" + timeLeft.toSecondsPart()
        ));
    }

    public static InvalidPasswordRecoveryTokenException getInvalidPasswordRecoveryTokenException(String token) {
        return new InvalidPasswordRecoveryTokenException("Invalid password recovery token: " + token);
    }

    public static ExpiredPasswordRecoveryTokenException getExpiredPasswordRecoveryTokenException(String token) {
        return new ExpiredPasswordRecoveryTokenException("Expired password recovery token: " + token);
    }
}
