package study111.commerce.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode(of = {"id"})
@Getter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @GeneratedValue
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    protected User() {
    }

    @Builder
    protected User(Long id, String username, String password, String email, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of("ROLE_USER").map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserEditor.UserEditorBuilder toEditor() {
        return UserEditor.builder()
            .username(this.username)
            .email(this.email)
            .address(this.address);
    }

    public void edit(UserEditor editor) {
        this.username = editor.getUsername();
        this.email = editor.getEmail();
        this.address = editor.getAddress();
    }
}
