package study111.commerce.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import study111.commerce.domain.User;
import study111.commerce.dto.ResponsePayload;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

public class JwtConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<JwtConfigurer<H>, H> {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public JwtConfigurer(JwtUtil jwtUtil, ObjectMapper objectMapper) {
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    public void init(H builder) throws Exception {
        super.init(builder);
    }

    @Override
    public void configure(H builder) throws Exception {
        super.configure(builder);

        var authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        var filter = createUsernamePasswordAuthenticationFilter(authenticationManager);

        builder.addFilter(filter);
        builder.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), AuthorizationFilter.class);
    }

    private UsernamePasswordAuthenticationFilter createUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        var filter = new UsernamePasswordAuthenticationFilter(authenticationManager);

        filter.setFilterProcessesUrl("/auth/token");
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());

        return filter;
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (req, res, auth) -> {
            var user = (User) auth.getPrincipal();

            var token = TokenResponsePayload.of(
                jwtUtil.generateToken(user.getId()),
                jwtUtil.generateToken(user.getId())
            );

            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.setCharacterEncoding(StandardCharsets.UTF_8.name());
            res.getWriter().print(objectMapper.writeValueAsString(ResponsePayload.of(token)));
        };
    }
}
