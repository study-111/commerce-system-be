package study111.commerce.security.jwt;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenResponsePayload {

    private String accessToken;
    private String refreshToken;

    public static TokenResponsePayload of(String accessToken, String refreshToken) {
        var payload = new TokenResponsePayload();

        payload.setAccessToken(accessToken);
        payload.setRefreshToken(refreshToken);

        return payload;
    }
}
