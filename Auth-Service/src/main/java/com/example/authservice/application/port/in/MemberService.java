package com.example.authservice.application.port.in;

import com.example.authservice.adaptor.in.web.dto.MemberInfo;
import com.example.authservice.adaptor.in.web.dto.MemberSignupRequest;

import java.util.UUID;

public interface MemberService {

    MemberInfo signupMember(MemberSignupRequest signupRequest);

    MemberInfo getMember(UUID memberId);
}
