package net.springboot.blog.springbootblogrestapi.security;

import io.jsonwebtoken.*;
import net.springboot.blog.springbootblogrestapi.exception.BlogApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }

    public String getUsernameFromJWT(String token){
        Claims  claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch(MalformedJwtException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "invalid JWT token");
        }catch(ExpiredJwtException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "expired JWT token");
        }catch(UnsupportedJwtException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "unsupported JWT token");
        }catch(IllegalArgumentException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }

    }
}
