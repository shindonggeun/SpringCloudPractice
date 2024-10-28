package com.example.authservice.application.service.mapper;

import com.example.authservice.adaptor.in.web.dto.MemberInfo;
import com.example.authservice.adaptor.in.web.dto.MemberSignupRequest;
import com.example.authservice.domain.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    // 회원가입 요청 DTO를 Member 엔티티로 변환
    Member toEntity(MemberSignupRequest signupRequest);

    // 저장된 Member 엔티티를 회원 정보 응답 DTO로 변환
    MemberInfo toMemberInfo(Member member);
}
