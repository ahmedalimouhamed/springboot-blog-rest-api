package net.springboot.blog.springbootblogrestapi.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class JwtUtil {
    public static void main(String[] args) {
        // Generate a secure HS512 key
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Convert the key to bytes
        byte[] keyBytes = key.getEncoded();

        // Encode the key to Base64
        String base64Key = java.util.Base64.getUrlEncoder().encodeToString(keyBytes);

        System.out.println("HS512 Key: " + base64Key);
    }
}
