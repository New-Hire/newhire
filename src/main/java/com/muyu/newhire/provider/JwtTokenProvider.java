package com.muyu.newhire.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private Long expirationTime;

    private SecretKey key;

    private SecretKey getKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        }
        return key;
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        Date expiredDate = new Date(new Date().getTime() + expirationTime);
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .expiration(expiredDate)
                .signWith(this.getKey(), Jwts.SIG.HS512)
                .compact();
    }

    public Claims verifyAndGetSubjectFromToken(String token) {
        return Jwts.parser()
                .verifyWith(this.getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
