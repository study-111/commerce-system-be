package study111.commerce.service;

import lombok.Getter;
import lombok.Setter;
import study111.commerce.domain.User;

@Setter
@Getter
public class UserJoinCommand {

    private String username;
    private String password;

    public User toEntity() {
        return User.of(username, password);
    }
}
