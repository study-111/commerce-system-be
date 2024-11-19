package study111.commerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import study111.commerce.security.jwt.JwtConfigurer;
import study111.commerce.security.jwt.JwtProperties;
import study111.commerce.security.jwt.JwtUtil;

@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private final ObjectMapper objectMapper;
    private final JwtProperties properties;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtUtil jwtUtil(JwtProperties properties) {
        return new JwtUtil(properties);
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        var provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .with(new JwtConfigurer<>(jwtUtil(properties), objectMapper), Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }
}
