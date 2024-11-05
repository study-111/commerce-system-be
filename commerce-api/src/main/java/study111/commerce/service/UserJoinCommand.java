package study111.commerce.service;

import lombok.Getter;
import lombok.Setter;
import study111.commerce.domain.User;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserJoinCommand {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public User toEntity() {
        return User.of(username, password);
    }
}
