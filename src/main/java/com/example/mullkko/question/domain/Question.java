package com.example.mullkko.question.domain;

import com.example.mullkko.session.domain.Session;
import com.example.mullkko.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String anonymousNickname;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Integer likeCount;

    @Column(nullable = false)
    private Boolean isAnswered;

    @Column(nullable = false)
    private Boolean isPinned;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Question(Session session, User author, String anonymousNickname, String content) {
        this.session = session;
        this.author = author;
        this.anonymousNickname = anonymousNickname;
        this.content = content;
        this.likeCount = 0;
        this.isAnswered = false;
        this.isPinned = false;
    }

    public void updateAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public void updatePinned(boolean isPinned) {
        this.isPinned = isPinned;
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }

    public void decrementLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }
}