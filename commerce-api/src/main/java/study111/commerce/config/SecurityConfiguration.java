package study111.commerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import study111.commerce.constant.JwtConstants;
import study111.commerce.domain.User;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var authenticationManager = authenticationConfiguration.getAuthenticationManager();
        var filter = getUsernamePasswordAuthenticationFilter(authenticationManager);
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(c ->
                c.anyRequest().authenticated()
            )
            .addFilterBefore(filter, AuthorizationFilter.class)
            .addFilterBefore(new JwtAuthenticationFilter(), AuthorizationFilter.class)
            .build();
    }

    private UsernamePasswordAuthenticationFilter getUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        var filter = new UsernamePasswordAuthenticationFilter(authenticationManager);

        filter.setFilterProcessesUrl("/auth/token");
        filter.setAuthenticationSuccessHandler((req, res, auth) -> {
            var user = (User) auth.getPrincipal();

            var token = new TokenResponsePayload();
            token.setAccessToken(generateToken(user.getId()));
            token.setRefreshToken(generateToken(user.getId()));

            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.getWriter().print(objectMapper.writeValueAsString(token));
        });
        return filter;
    }

    private String generateToken(Long id) {
        SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.KEY.getBytes());

        return Jwts.builder()
            .signWith(secretKey, Jwts.SIG.HS256)
            .header().type("JWT")
            .and()
            .claims()
            .add("id", String.valueOf(id))
            .and()
            .compact();
    }
}
