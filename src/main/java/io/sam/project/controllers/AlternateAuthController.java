package io.sam.project.controllers;

import io.sam.project.payload.request.LoginRequest;
import io.sam.project.payload.request.SignupRequest;
import io.sam.project.securityAlt.services.UserServiceAlt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth2")
@RequiredArgsConstructor
public class AlternateAuthController {

    private final UserServiceAlt userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.signin(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return userService.signup(signUpRequest);
    }
}
