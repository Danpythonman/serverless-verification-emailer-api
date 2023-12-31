package com.danieldigiovanni.auth;

import com.danieldigiovanni.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;

import java.security.Key;
import java.util.Date;

@AllArgsConstructor
public class JwtService {

    private final Long jwtDurationMillis;
    private final String jwtSecretKey;

    /**
     * Generates a JWT for a given user. The user's email will be the subject of
     * the JWT.
     *
     * @param user The user being issued the JWT. Their email
     *             will be the subject of the JWT.
     * @return The generated JWT.
     */
    public String generateToken(User user) {
        long issuedAtMillis = System.currentTimeMillis();
        long expiredAtMillis = issuedAtMillis + this.jwtDurationMillis;

        return Jwts.builder()
            .setSubject(user.getEmail())
            .setIssuedAt(new Date(issuedAtMillis))
            .setExpiration(new Date(expiredAtMillis))
            .signWith(
                this.getSigningKey(),
                SignatureAlgorithm.HS256
            )
            .compact();
    }

    /**
     * Extracts the claims from a JWT.
     *
     * @param jwt The JWT from which to extract the claims.
     *
     * @return The claims of the JWT.
     *
     * @throws IllegalArgumentException If the JWT is null, empty, or only
     *                                  whitespace.
     * @throws MalformedJwtException    If the JWT does not have a valid
     *                                  format.
     * @throws UnsupportedJwtException  If the JWT claims do not have a valid
     *                                  format.
     * @throws ExpiredJwtException      If the JWT is expired.
     * @throws SignatureException       If the JWT signature validation fails.
     */
    public Claims extractClaimsFromToken(String jwt) throws IllegalArgumentException, MalformedJwtException, UnsupportedJwtException, ExpiredJwtException, SignatureException {
        JwtParser jwtParser = Jwts.parserBuilder()
            .setSigningKey(this.getSigningKey())
            .build();

        return jwtParser.parseClaimsJws(jwt).getBody();
    }

    /**
     * Generates a signing key with the HMAC-SHA algorithm based on the JWT
     * secret key.
     *
     * @return The signing key.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
