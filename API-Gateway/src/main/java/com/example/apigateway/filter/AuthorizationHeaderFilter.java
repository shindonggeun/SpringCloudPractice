package com.example.apigateway.filter;

import com.example.apigateway.jwt.JwtTokenVerifier;
import com.example.apigateway.jwt.exception.JwtTokenErrorCode;
import com.example.apigateway.jwt.exception.JwtTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import com.example.apigateway.filter.AuthorizationHeaderFilter.Config;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<Config> {

    static class Config {

    }

    private final JwtTokenVerifier jwtTokenVerifier;
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            String jwt = getJwtFrom(request);

            try {
                // JWT 토큰 검증
                String memberId = jwtTokenVerifier.parseAccessTokenGetMemberId(jwt);

                // 추출한 memberId를 요청 헤더에 추가
                addAuthorizationHeaders(request, memberId);
            } catch (ExpiredJwtException e) {
                throw new JwtTokenException(JwtTokenErrorCode.EXPIRED_TOKEN);
            } catch (MalformedJwtException | SecurityException | IllegalArgumentException e) {
                throw new JwtTokenException(JwtTokenErrorCode.INVALID_TOKEN);
            } catch (SignatureException e) {
                throw new JwtTokenException(JwtTokenErrorCode.SIGNATURE_INVALID);
            }

            return chain.filter(exchange);
        };
    }

    private String getJwtFrom(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        log.info("요청 : {} / 액세스 토큰 값: {}", request.getURI(), bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    private void addAuthorizationHeaders(ServerHttpRequest request, String memberId) {
        request.mutate()
                .header("X-Authorization-Id", memberId)
                .build();
    }
}
