package com.example.authservice.application.service;

import com.example.authservice.adaptor.in.web.dto.MemberInfo;
import com.example.authservice.adaptor.in.web.dto.MemberSignupRequest;
import com.example.authservice.application.port.in.MemberService;
import com.example.authservice.application.port.out.MemberRepoPort;
import com.example.authservice.application.service.mapper.MemberMapper;
import com.example.authservice.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepoPort memberRepoPort;
    private final MemberMapper memberMapper;

    @Override
    public MemberInfo signupMember(MemberSignupRequest signupRequest) {

        if (memberRepoPort.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        Member member = memberMapper.toEntity(signupRequest);

        Member savedMember = memberRepoPort.save(member);

        return memberMapper.toMemberInfo(savedMember);
    }

    @Override
    public MemberInfo getMember(UUID memberId) {
        Member member = memberRepoPort.findById(memberId);
        return memberMapper.toMemberInfo(member);
    }
}
