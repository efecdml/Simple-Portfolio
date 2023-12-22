package com.gungorefe.simpleportfolio.repository.user;

import com.gungorefe.simpleportfolio.entity.user.PasswordRecoveryToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRecoveryTokenRepository extends JpaRepository<PasswordRecoveryToken, Long> {
    Optional<PasswordRecoveryToken> findByUser_Email(String email);

    Optional<PasswordRecoveryToken> findByToken(String token);
}
