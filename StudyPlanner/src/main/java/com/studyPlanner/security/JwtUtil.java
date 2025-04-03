package com.studyPlanner.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static  final String SECRET_KEY = "MySecretKeyForJWTGenerationMySecretKey";
    private  static  final long EXPIRATION_TIME = 1000 * 60 * 60;

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public  String extractUsername(String token){
        return  extractClaim(token,Claims::getSubject);
    }

    public boolean validateToken(String token,String username){
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody();
        return  claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token){
        return  extractClaim(token,Claims::getExpiration).before(new Date());
    }
}
