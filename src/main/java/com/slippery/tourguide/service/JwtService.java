package com.slippery.tourguide.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final String SECRET_KEY ="3f2b962dd982d0d14316f718646a4cadc049d91ff301d4028245a243946414df730786fc29ff766ca2b56bb85935f6c4798c57f1a456cb4153076c0c9f29515892deb059c7fb643ccf8422d44e624f7cd3cc61bb1f8f665f5218343c674e7910b4968bec631b1c174325dda049ed3a71ba0f0e0a5b29830a1d7306651eb08bd6fd8fdf78ed313eed9cd829b2e33dba1e8efadd1fdb3100b8bf75fb512f5ee6321fed4326df81603662b4741d3a5d096ef4d6f80d939f7fc13e8306fb145425be817322f1dfc2648d8e3a328bc88d156e8f7b10cafce6313e935d7c9895086a79ac7fc63ef381dff3138ccbcdcab67a0896d145a85a7c109c22264a12fe26ce05";
    private final Long EXPIRATION_TIME =864000L;

    protected SecretKey generateSecretKey(){
        byte [] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateJwtToken(String username){
        Map<String,Object> claims =new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .and()
                .signWith(generateSecretKey())
                .compact();
    }
}
