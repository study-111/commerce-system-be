package study111.commerce.security.jwt;

import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

                var authentication = JwtAuthenticationToken.authenticated(Long.valueOf(id), "USER");

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (SignatureException e) {
                var message = e.getMessage();
                log.error("message = {}", message);

                throw new BadCredentialsException(message);
            }
        }

        filterChain.doFilter(request, response);
    }
}
