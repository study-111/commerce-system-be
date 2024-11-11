package study111.commerce.security.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class TokenResponsePayload {

    private String accessToken;
    private String refreshToken;
}
