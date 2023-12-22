package com.gungorefe.simpleportfolio.controller;

import com.gungorefe.simpleportfolio.dto.user.CreateUserRequest;
import com.gungorefe.simpleportfolio.dto.user.UpdateUserRequest;
import com.gungorefe.simpleportfolio.dto.user.UserDto;
import com.gungorefe.simpleportfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateUserRequest request) {
        service.create(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserDto> getDto() {
        return ResponseEntity.ok(service.getDto());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<Set<UserDto>> getAllDtos() {
        return ResponseEntity.ok(service.getAllDtos());
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateUserRequest request) {
        service.update(request);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email) {
        service.delete(email);

        return ResponseEntity.ok().build();
    }
}
