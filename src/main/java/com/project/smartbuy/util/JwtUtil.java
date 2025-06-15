package com.project.smartbuy.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY= "QX28i9Nxh4T8nqUv90pX1BgZV5KU6dG2aMPrlz7wAykX3b5tJcR";


    // Generate JWT
    public String generateToken(String username){
        long exprirationMillis= 24 * 60 * 60 * 1000;// // valid for 1 day
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exprirationMillis))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

    //JWT Varification
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
    //Parse username
    public String extractUsername(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes()).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
