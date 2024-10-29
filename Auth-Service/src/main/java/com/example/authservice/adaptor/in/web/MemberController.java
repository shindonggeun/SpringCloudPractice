package com.example.authservice.adaptor.in.web;

import com.example.authservice.adaptor.in.web.dto.MemberInfo;
import com.example.authservice.adaptor.in.web.dto.MemberSignupRequest;
import com.example.authservice.application.port.in.MemberService;
import com.example.globalmodule.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Message<MemberInfo>> signupMember(@RequestBody MemberSignupRequest signupRequest) {
        MemberInfo memberInfo = memberService.signupMember(signupRequest);
        return ResponseEntity.ok(Message.success(memberInfo));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Message<MemberInfo>> getMember(@PathVariable("memberId") UUID memberId) {
        MemberInfo memberInfo = memberService.getMember(memberId);
        return ResponseEntity.ok(Message.success(memberInfo));
    }
}
