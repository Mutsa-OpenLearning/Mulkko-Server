package com.example.mullkko.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "kakao_id", nullable = false, unique = true)
    private String kakaoId;

    @Column(name = "nickname")
    private String nickname;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.nickname == null || this.nickname.isBlank()) {
            this.nickname = "익명 사자 #" + (int)(Math.random() * 900 + 100);
        }
    }

    public User(String kakaoId, String nickname) {
        this.kakaoId = kakaoId;
        this.nickname = (nickname != null) ? nickname : "익명 사자 #" + (int)(Math.random() * 900 + 100);
        this.createdAt = LocalDateTime.now();
    }
}