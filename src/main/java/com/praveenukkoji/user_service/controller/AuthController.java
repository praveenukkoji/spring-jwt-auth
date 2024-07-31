package com.praveenukkoji.user_service.controller;

import com.praveenukkoji.user_service.dto.AuthenticateRequest;
import com.praveenukkoji.user_service.dto.AuthenticateResponse;
import com.praveenukkoji.user_service.dto.RegisterRequest;
import com.praveenukkoji.user_service.exception.RoleNotFoundException;
import com.praveenukkoji.user_service.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest)
            throws RoleNotFoundException {
        authService.register(registerRequest);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(
            @RequestBody @Valid AuthenticateRequest authenticateRequest
    ) {
        return ResponseEntity.status(200).body(authService.authenticate(authenticateRequest));
    }
}
