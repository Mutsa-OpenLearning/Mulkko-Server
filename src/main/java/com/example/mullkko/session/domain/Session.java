package com.example.mullkko.session.domain;

import com.example.mullkko.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sessions")
public class Session {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    private User host;

    @Column(nullable = false)
    private String title;

    // 2. 세션 설명
    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    @Column(nullable = false, unique = true)
    private String sessionCode;

    private LocalDateTime createdAt;

    private Session(User host, String title, String description, String imageUrl, String sessionCode) {
        this.host = host;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.sessionCode = sessionCode;
        this.createdAt = LocalDateTime.now();
    }
    public static Session createSession(User host, String title, String description, String imageUrl, String sessionCode){
        return new Session(host, title, description, imageUrl, sessionCode);
    }
}
