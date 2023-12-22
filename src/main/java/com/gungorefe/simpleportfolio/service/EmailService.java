package com.gungorefe.simpleportfolio.service;

import com.gungorefe.simpleportfolio.config.JavaMailSenderConfig;
import com.gungorefe.simpleportfolio.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Async
    public void send(EmailDto dto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(JavaMailSenderConfig.serverEmail);
        mailMessage.setTo(dto.email());
        mailMessage.setSubject(dto.subject());
        mailMessage.setText(dto.body());

        javaMailSender.send(mailMessage);
    }
}
