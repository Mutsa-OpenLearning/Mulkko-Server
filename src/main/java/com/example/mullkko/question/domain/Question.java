package com.example.mullkko.question.domain;

import com.example.mullkko.session.domain.Session;
import com.example.mullkko.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String nickname;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private Double positionX; // 화면 가로 위치 비율 (예: 100.0)
    private Double positionY; // 화면 세로 위치 비율 (예: 45.5)

    private int likeCount = 0;

    private LocalDateTime createdAt;

    private Question(Session session, User author, String content, Double positionX, Double positionY) {
        this.session = session;
        this.author = author;
        this.nickname = (author.getNickname() != null) ? author.getNickname() : "익명 사용자";
        this.content = content;
        this.positionX = (positionX != null) ? positionX : 100.0;
        this.positionY = (positionY != null) ? positionY : 50.0;
        this.likeCount = 0;
        this.createdAt = LocalDateTime.now();
    }

    public static Question createQuestion(Session session, User author, String content, Double positionX, Double positionY) {
        return new Question(session, author, content, positionX, positionY);
    }

    public void increaseLike() {
        this.likeCount++;
    }

    public void decreaseLike() {
        if (this.likeCount > 0) this.likeCount--;
    }
}
