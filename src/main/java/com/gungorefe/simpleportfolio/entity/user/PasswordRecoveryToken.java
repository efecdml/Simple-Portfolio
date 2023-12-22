package com.gungorefe.simpleportfolio.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Table(name = "password_recovery_tokens")
@Entity
public class PasswordRecoveryToken {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    private String token;
    private LocalDateTime expirationDate;
    private boolean sent;
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public PasswordRecoveryToken(String token, LocalDateTime expirationDate, boolean sent, User user) {
        this.token = token;
        this.expirationDate = expirationDate;
        this.sent = sent;
        this.user = user;
    }
}
