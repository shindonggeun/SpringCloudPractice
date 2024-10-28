package com.example.authservice.adaptor.in.web;

import com.example.authservice.adaptor.in.web.dto.MemberInfo;
import com.example.authservice.adaptor.in.web.dto.MemberSignupRequest;
import com.example.authservice.application.port.in.MemberService;
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
    public ResponseEntity<MemberInfo> signupMember(@RequestBody MemberSignupRequest signupRequest) {
        return ResponseEntity.ok(memberService.signupMember(signupRequest));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberInfo> getMember(@PathVariable("memberId") UUID memberId) {
        return ResponseEntity.ok(memberService.getMember(memberId));
    }
}
