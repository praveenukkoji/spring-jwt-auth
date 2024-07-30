package com.praveenukkoji.user_service.service;

import com.praveenukkoji.user_service.config.JwtService;
import com.praveenukkoji.user_service.dto.AuthenticateRequest;
import com.praveenukkoji.user_service.dto.AuthenticateResponse;
import com.praveenukkoji.user_service.dto.RegisterRequest;
import com.praveenukkoji.user_service.exception.RoleNotFound;
import com.praveenukkoji.user_service.model.Role;
import com.praveenukkoji.user_service.model.User;
import com.praveenukkoji.user_service.repository.RoleRepository;
import com.praveenukkoji.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public void register(RegisterRequest registerRequest) throws RoleNotFound {
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RoleNotFound("role not found"));

        User user = User.builder()
                .fullname(registerRequest.getFullname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .isActive(true)
                .roleList(List.of(role))
                .build();

        userRepository.save(user);
    }

    public AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getEmail(),
                        authenticateRequest.getPassword()
                )
        );

        var claims = new HashMap<String, Object>();

        var user = (User) auth.getPrincipal();

        claims.put("fullName", user.getFullname());

        var jwt = jwtService.generateToken(claims, user);

        return AuthenticateResponse.builder().token(jwt).build();

    }
}
