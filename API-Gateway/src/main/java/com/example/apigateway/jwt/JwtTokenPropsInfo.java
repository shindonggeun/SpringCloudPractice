package com.example.apigateway.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtTokenPropsInfo(
        String accessKey
) {
}
