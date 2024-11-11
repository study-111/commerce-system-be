package study111.commerce.service;

import lombok.Getter;
import lombok.Setter;
import study111.commerce.domain.User;

import javax.validation.constraints.NotBlank;
import java.util.function.Function;

@Setter
@Getter
public class UserJoinCommand {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public User toEntity(Function<String, String> passwordEncoder) {
        return User.of(username, passwordEncoder.apply(password));
    }
}
