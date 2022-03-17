package io.sam.project.securityAlt;

import io.sam.project.payload.request.LoginRequest;
import io.sam.project.payload.request.SignupRequest;
import io.sam.project.securityAlt.services.UserServiceTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth2")
public class AuthControllerTwo {
    @Autowired
    private UserServiceTwo userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.signin(loginRequest);
    }

    // https://github.com/koushikkothagal/spring-security-jwt

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return userService.signup(signUpRequest);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        return userService.logout();
    }
}
