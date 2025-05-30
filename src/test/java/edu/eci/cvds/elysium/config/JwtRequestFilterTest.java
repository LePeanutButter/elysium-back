package edu.eci.cvds.elysium.config;

import static org.mockito.Mockito.*;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import edu.eci.cvds.elysium.service.CustomUserDetailsService;
import edu.eci.cvds.elysium.util.JwtUtil;



public class JwtRequestFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private FilterChain chain;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;
    
    private JwtRequestFilter jwtRequestFilter;

    private AutoCloseable closeable;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        jwtRequestFilter = new JwtRequestFilter(jwtUtil, customUserDetailsService);
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    public void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
        closeable.close();
    }

    @Test
    public void testSkipFilterForUserCreation() throws ServletException, IOException {
        // When URI is for user creation and method is POST, filter passes through
        when(request.getRequestURI()).thenReturn("/api/usuario/usuario");
        when(request.getMethod()).thenReturn("POST");

        jwtRequestFilter.doFilterInternal(request, response, chain);

        verify(chain, times(1)).doFilter(request, response);
        // No authentication should be set
        assert(SecurityContextHolder.getContext().getAuthentication() == null);
    }
    
    @Test
    public void testValidTokenAuthenticationSet() throws ServletException, IOException {
        String tokenHeader = "Bearer validToken";
        String jwt = "validToken";
        String username = "testUser";

        when(request.getHeader("Authorization")).thenReturn(tokenHeader);
        when(request.getRequestURI()).thenReturn("/some/other/uri");
        when(request.getMethod()).thenReturn("GET");
        when(jwtUtil.extractUsername(jwt)).thenReturn(username);

        // Create a dummy UserDetails object
        UserDetails userDetails = User.withUsername(username).password("password").authorities("ROLE_USER").build();
        when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtUtil.validateToken(jwt, userDetails)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, chain);

        // Authentication should be set in the SecurityContext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert(auth != null);
        assert(auth instanceof UsernamePasswordAuthenticationToken);
        assert(userDetails.equals(auth.getPrincipal()));

        verify(chain, times(1)).doFilter(request, response);
    }
    
    @Test
    public void testInvalidTokenDoesNotSetAuthentication() throws ServletException, IOException {
        String tokenHeader = "Bearer invalidToken";
        String jwt = "invalidToken";
        String username = "testUser";

        when(request.getHeader("Authorization")).thenReturn(tokenHeader);
        when(request.getRequestURI()).thenReturn("/other/uri");
        when(request.getMethod()).thenReturn("GET");
        when(jwtUtil.extractUsername(jwt)).thenReturn(username);

        UserDetails userDetails = User.withUsername(username).password("password").authorities("ROLE_USER").build();
        when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtUtil.validateToken(jwt, userDetails)).thenReturn(false);

        jwtRequestFilter.doFilterInternal(request, response, chain);

        // No authentication should be set when token is invalid
        assert(SecurityContextHolder.getContext().getAuthentication() == null);
        verify(chain, times(1)).doFilter(request, response);
    }
    
    @Test
    public void testNoTokenHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);
        when(request.getRequestURI()).thenReturn("/other/uri");
        when(request.getMethod()).thenReturn("GET");

        jwtRequestFilter.doFilterInternal(request, response, chain);

        // Without an Authorization header, no authentication is set
        assert(SecurityContextHolder.getContext().getAuthentication() == null);
        verify(chain, times(1)).doFilter(request, response);
    }
}