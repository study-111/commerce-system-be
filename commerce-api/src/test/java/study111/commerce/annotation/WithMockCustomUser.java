package study111.commerce.annotation;

import org.springframework.security.test.context.support.WithSecurityContext;
import study111.commerce.config.WithMockCustomUserSecurityContextFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

    long id() default 1L;

    String username() default "tester";

    String[] roles() default {"USER"};
}
