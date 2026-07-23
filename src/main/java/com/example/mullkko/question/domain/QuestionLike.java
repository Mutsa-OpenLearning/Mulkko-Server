package com.example.mullkko.question.domain;

import com.example.mullkko.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question_likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"question_id", "user_id"})
})
public class QuestionLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public QuestionLike(Question question, User user) {
        this.question = question;
        this.user = user;
    }
}
