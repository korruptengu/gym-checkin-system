package com.korruptengu.gymcheckinsystem.controller.auth;

import com.korruptengu.gymcheckinsystem.auth.JwtService;
import com.korruptengu.gymcheckinsystem.dto.request.login.LoginRequest;
import com.korruptengu.gymcheckinsystem.dto.response.LoginResponse;
import com.korruptengu.gymcheckinsystem.entity.AppUser;
import com.korruptengu.gymcheckinsystem.enums.UserRole;
import com.korruptengu.gymcheckinsystem.security.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthController authController;

    @Test
    void login_shouldReturnOk_whenAuthenticationSuccessful() {
        LoginRequest request = new LoginRequest("user", "pass");
        AppUser user = new AppUser();
        user.setUsername("user");
        user.setPassword("pass");
        user.setURole(UserRole.MEMBER);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(userDetails);
        when(authManager.authenticate(any())).thenReturn(auth);
        when(jwtService.generateToken(user)).thenReturn("jwt-token");

        ResponseEntity<LoginResponse> response = authController.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("jwt-token", response.getBody().token());
        assertEquals("user", response.getBody().username());
        assertEquals("MEMBER", response.getBody().role());
    }

    @Test
    void login_shouldReturnUnauthorized_whenAuthenticationFails() {
        LoginRequest request = new LoginRequest("user", "wrong-pass");
        when(authManager.authenticate(any())).thenThrow(new BadCredentialsException("Bad credentials"));

        ResponseEntity<LoginResponse> response = authController.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
        verify(jwtService, never()).generateToken(any());
    }

    @Test
    void login_shouldReturnBadRequest_whenUsernameOrPasswordMissing() {
        // Arrange
        LoginRequest request = new LoginRequest("", "");

        // Act
        ResponseEntity<LoginResponse> response = authController.login(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
