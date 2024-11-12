package study111.commerce.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserDetails user;

    public JwtAuthenticationToken(UserDetails user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);

        this.user = user;
    }

    public static JwtAuthenticationToken authenticated(UserDetails user, String role) {
        return new JwtAuthenticationToken(user, List.of(new SimpleGrantedAuthority("ROLE_" + role)));
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
