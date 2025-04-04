package edu.eci.cvds.elysium.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;






public class JwtUtilTest {

    private JwtUtil jwtUtil;
    private UserDetails dummyUser;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil();
        dummyUser = new UserDetails() {
            @Override
            public Collection getAuthorities() {
                return Collections.emptyList();
            }
            @Override
            public String getPassword() {
                return "password";
            }
            @Override
            public String getUsername() {
                return "testuser";
            }
            @Override
            public boolean isAccountNonExpired() {
                return true;
            }
            @Override
            public boolean isAccountNonLocked() {
                return true;
            }
            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }
            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

    @Test
    public void testGenerateToken() {
        String token = jwtUtil.generateToken(dummyUser);
        assertNotNull(token, "Token should not be null.");
    }

    @Test
    public void testExtractUsername() {
        String token = jwtUtil.generateToken(dummyUser);
        String username = jwtUtil.extractUsername(token);
        assertEquals(dummyUser.getUsername(), username, "Extracted username should match the dummy user.");
    }

    @Test
    public void testExtractExpiration() {
        String token = jwtUtil.generateToken(dummyUser);
        Date expiration = jwtUtil.extractExpiration(token);
        assertNotNull(expiration, "Expiration date should not be null.");
        assertTrue(expiration.after(new Date()), "Expiration date should be in the future.");
    }

    @Test
    public void testValidateToken() {
        String token = jwtUtil.generateToken(dummyUser);
        assertTrue(jwtUtil.validateToken(token, dummyUser), "The token should be valid for the dummy user.");
    }

    @Test
    public void testInvalidToken() {
        String token = jwtUtil.generateToken(dummyUser);
        // Tamper the token by modifying a character at the end to simulate an invalid signature.
        String tamperedToken = token.substring(0, token.length() - 1) + "x";
        // Expect an exception when trying to parse the tampered token.
        assertThrows(Exception.class, () -> jwtUtil.extractUsername(tamperedToken), "Tampered token should throw an exception.");
    }
}