package rest.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class JWTHelper {

    private byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    private Key signingKey = new SecretKeySpec(apiKeySecretBytes, "SHA-256");
    private static final String SECRET_KEY = "secret";

    /***
     * generates a json web token with the parameters as payload.
     */
    public String generatePrivateKey(String id, String email, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("id", id);
        return Jwts
                .builder()
                .setSubject(email)
                .addClaims(claims)
                .setExpiration(calcExpirationDate())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    public Map claimKey(String jwsString) {
        Jwt jwt = Jwts.parser().setSigningKey(signingKey).parse(jwsString);
        Claims claims = (Claims) jwt.getBody();
        Map hashMap = new HashMap();
        hashMap.put("email", claims.getSubject());
        hashMap.put("id", claims.get("id"));
        hashMap.put("roles", claims.get("roles"));
        return hashMap;
    }

    private Date calcExpirationDate() {
        // Token is valid for 1 hour
        return Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
    }
}
