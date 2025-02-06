package com.human.simpleexamplespringsecurityjwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey1234mysecretkeymysecretkeymysecretkey1234"; //At least 256 bits

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }


    public String generateToken(String username){

        return Jwts.builder().claims()
                .add("sub",username) //subject
                .add("iat", new Date()) //issue at
                .add("exp",new Date(System.currentTimeMillis() +2 *60*1000)) //Expiration time
                .and()
                .signWith(getSigningKey()) // correct signing method
                .compact();


    }

    //Extract a specific claim from JWT token
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    // extract username from JWT token
    public String extractUsername(String token){
        return extractClaim(token,claims -> claims.get("sub",String.class));
    }

    //extract expiration date from JWT token
    public Date extractExpiration(String token){
        return extractClaim(token,claims -> claims.get("exp",Date.class));
    }

    // check if token is expired
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // validate token by checking username and expiration
    public boolean validateToken(String token,String username){
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // extract all claims from jwt token

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey()) // verify signature
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }



}
