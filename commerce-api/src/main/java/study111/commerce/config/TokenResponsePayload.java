package study111.commerce.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenResponsePayload {
    private String accessToken;
    private String refreshToken;
}
