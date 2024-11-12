package study111.commerce.config;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import study111.commerce.annotation.WithMockCustomUser;
import study111.commerce.domain.User;

import java.util.Arrays;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        var context = TestSecurityContextHolder.getContext();

        var principal = User.builder()
            .id(annotation.id())
            .username(annotation.username())
            .build();
        var authorities = Arrays.stream(annotation.roles()).map(role -> "ROLE_" + role)
            .toArray(String[]::new);
        var authentication = new TestingAuthenticationToken(principal, null, authorities);

        authentication.setAuthenticated(true);

        context.setAuthentication(authentication);

        return context;
    }
}
