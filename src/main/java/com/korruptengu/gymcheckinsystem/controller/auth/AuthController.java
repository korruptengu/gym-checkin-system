package com.korruptengu.gymcheckinsystem.controller.auth;

import com.korruptengu.gymcheckinsystem.auth.JwtService;
import com.korruptengu.gymcheckinsystem.dto.request.login.LoginRequest;
import com.korruptengu.gymcheckinsystem.dto.response.LoginResponse;
import com.korruptengu.gymcheckinsystem.entity.AppUser;
import com.korruptengu.gymcheckinsystem.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.AUTH;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        if (request.username() == null || request.username().isBlank() ||
                request.password() == null || request.password().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            AppUser user = userDetails.getAppUser();

            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(
                    new LoginResponse(
                            token,
                            userDetails.getUsername(),
                            userDetails.getUserRole().toString()));
        } catch (AuthenticationException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }
}
