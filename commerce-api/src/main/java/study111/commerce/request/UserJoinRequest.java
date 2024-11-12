package study111.commerce.request;

import java.util.function.Function;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import study111.commerce.domain.User;

@Setter
@Getter
public class UserJoinRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public User toEntity(Function<String, String> passwordEncoder) {
        return User.builder().username(username).password(passwordEncoder.apply(password)).build();
    }
}
