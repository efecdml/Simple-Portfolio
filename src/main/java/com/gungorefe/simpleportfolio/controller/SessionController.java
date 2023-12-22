package com.gungorefe.simpleportfolio.controller;

import com.gungorefe.simpleportfolio.dto.session.LoginRequest;
import com.gungorefe.simpleportfolio.dto.session.LoginResponse;
import com.gungorefe.simpleportfolio.dto.session.PasswordDto;
import com.gungorefe.simpleportfolio.service.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/session")
@RestController
public class SessionController {
    private final SessionService service;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        LoginResponse response = service.login(request);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.SET_COOKIE,
                        response.cookie().toString()
                )
                .body(response.role());
    }

    @DeleteMapping
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.SET_COOKIE,
                        service.logout().toString()
                )
                .build();
    }

    @GetMapping("/password-recovery/username/{username}")
    public ResponseEntity<Void> createPasswordRecoveryToken(
            @PathVariable String username,
            @RequestParam(name = "locale") String localeName
    ) {
        service.createPasswordRecoveryToken(
                username,
                localeName
        );

        return ResponseEntity.ok().build();
    }

    @PutMapping("/password-recovery/token/{token}")
    public ResponseEntity<Void> recoverPassword(
            @PathVariable String token,
            @RequestBody PasswordDto dto
    ) {
        service.recoverPassword(
                token,
                dto
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/check")
    public ResponseEntity<Void> check() {
        return ResponseEntity.ok().build();
    }
}
