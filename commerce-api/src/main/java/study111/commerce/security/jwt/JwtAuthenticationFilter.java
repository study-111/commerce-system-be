package study111.commerce.security.jwt;

import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import study111.commerce.domain.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith(AUTHENTICATION_SCHEME_BEARER)) {
            var accessToken = authorization.substring(AUTHENTICATION_SCHEME_BEARER.length()).trim();

            try {
                var id = jwtUtil.getClaim(accessToken, "id", String.class);
                var username = jwtUtil.getClaim(accessToken, "sub", String.class);

                // ...
                var authentication = JwtAuthenticationToken.authenticated(User.builder()
                    .id((Long.valueOf(id)))
                    .username(username)
                    .build(), "USER");

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (SignatureException e) {
                var message = e.getMessage();
                log.error("message = {}", message);

//                throw new BadCredentialsException(message);
            }
        }

        filterChain.doFilter(request, response);
    }
}
