package com.example.authservice.application.port.out;

import com.example.authservice.domain.Member;

import java.util.UUID;

public interface MemberRepoPort {

    boolean existsByEmail(String email);

    Member save(Member member);

    Member findById(UUID id);
}
