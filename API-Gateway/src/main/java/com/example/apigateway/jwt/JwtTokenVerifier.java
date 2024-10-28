package com.example.apigateway.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenVerifier {

    private final JwtTokenPropsInfo tokenPropsInfo;

    public String parseAccessTokenGetMemberId(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(tokenPropsInfo.accessKey().getBytes()))
                .build()
                .parseSignedClaims(token).getPayload().getId();
    }
}
