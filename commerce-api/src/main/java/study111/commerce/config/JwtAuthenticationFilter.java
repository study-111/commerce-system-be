package study111.commerce.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import study111.commerce.constant.JwtConstants;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("JwtAuthenticationFilter.doFilterInternal");

        var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith(AUTHENTICATION_SCHEME_BEARER)) {
            // TODO authentication JWT
            var accessToken = authorization.substring(AUTHENTICATION_SCHEME_BEARER.length()).trim();


            SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.KEY.getBytes());
            var parser = Jwts.parser().verifyWith(secretKey).build();
            try {

                var payload = parser.parseSignedClaims(accessToken).getPayload();
                var id = payload.get("id", String.class);
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
