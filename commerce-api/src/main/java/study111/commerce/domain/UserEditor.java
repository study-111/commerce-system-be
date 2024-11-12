package study111.commerce.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserEditor {

    private String username;
    private String email;
    private String address;

    @Builder
    public UserEditor(String username, String email, String address) {
        this.username = username;
        this.email = email;
        this.address = address;
    }
}
