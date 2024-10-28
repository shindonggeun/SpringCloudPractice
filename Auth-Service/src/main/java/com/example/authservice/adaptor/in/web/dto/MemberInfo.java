package com.example.authservice.adaptor.in.web.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MemberInfo(
        UUID id,
        String email
) {
}
