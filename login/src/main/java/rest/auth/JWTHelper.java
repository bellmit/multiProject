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
    public String generatePrivateKey(String userName, List<String> roles) {
        Map<String, Object> groups = new HashMap<>();
        groups.put("roles", roles);
        return Jwts
                .builder()
                .setSubject(userName)
                .addClaims(groups)
                .setExpiration(calcExpirationDate())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    public List<String> claimKey(String jwsString) {
        Jwt jwt = Jwts.parser().setSigningKey(signingKey).parse(jwsString);
        Claims claims = (Claims) jwt.getBody();
        return (List<String>) claims.get("roles");
    }

    private Date calcExpirationDate() {
        // Token is valid for 1 hour
        return Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
    }
}
