package study111.commerce.security.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtUtil {
    private final JwtProperties properties;

    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;

    public JwtUtil(JwtProperties properties) {
        this.properties = properties;

        SecretKey secretKey = Keys.hmacShaKeyFor(properties.getKey().getBytes());

        this.jwtBuilder = Jwts.builder().signWith(secretKey, Jwts.SIG.HS256).header().type("JWT").and();
        this.jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }

    public String generateToken(Long id, String username) {
        return jwtBuilder
            .claims()
            .subject(username)
            .add("id", String.valueOf(id))
            // TODO: need to add more claims(iat, exp, sub, etc.)
            .and()
            .compact();
    }

    public <T> T getClaim(String token, String claim, Class<T> clazz) {
        var payload = jwtParser.parseSignedClaims(token).getPayload();

        return payload.get(claim, clazz);
    }
}
