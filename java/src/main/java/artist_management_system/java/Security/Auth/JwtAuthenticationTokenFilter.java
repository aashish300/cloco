package artist_management_system.java.Security.Auth;

import artist_management_system.java.Security.Model.JwtAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationTokenFilter() {
        super("/api/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Token ")) {
            throw new BadCredentialsException("Invalid JWT token");
        }

        String authenticationToken = header.substring(6);

        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(authRequest);
    }

    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
