package com.example.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@Data
@ConfigurationProperties(prefix = "markerhub.jwt")
public class JwtUtil {
    private String secret;
    private long expire;
    private String header;

    public String generateToken(long userId) {
        Date nowData = new Date();
        Date expireData = new Date(nowData.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(nowData)
                .setExpiration(expireData)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("exception{}", e);
            return null;

        }
    }

    public boolean tokenExpired(Date expire){
        return expire.before(new Date());

    }
}
