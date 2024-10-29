package com.example.authservice.domain;

import com.example.globalmodule.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @Comment("회원 아이디")
    @GeneratedValue(strategy = GenerationType.UUID)  // UUID 전략 설정
    @Column(columnDefinition = "UUID")
    private UUID id;  // UUID 타입

    @Comment("이메일")
    @Column(nullable = false)
    private String email;
}
