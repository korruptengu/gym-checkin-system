package com.korruptengu.gymcheckinsystem.auth;

import com.korruptengu.gymcheckinsystem.entity.AppUser;
import com.korruptengu.gymcheckinsystem.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "jwtSecret", "mein-super-geheimer-schluessel-ist-so-gut-darauf-kommt-keiner");
        ReflectionTestUtils.setField(jwtService, "expirationMs", 3600000L);
    }

    @Test
    void generateToken_shouldContainUsernameAndRole() {
        AppUser user = new AppUser();
        user.setUsername("user");
        user.setURole(UserRole.MEMBER);

        String token = jwtService.generateToken(user);

        assertTrue(jwtService.isTokenValid(token, "user"));
        assertEquals("user", jwtService.extractUsername(token));
    }
}
