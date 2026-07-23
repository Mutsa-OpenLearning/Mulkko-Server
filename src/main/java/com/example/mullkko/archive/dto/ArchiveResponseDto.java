package com.example.mullkko.archive.dto;

import com.example.mullkko.question.domain.Question;
import com.example.mullkko.question.domain.QuestionLike;
import com.example.mullkko.session.domain.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ArchiveResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SessionCardDto {
        private Long sessionId;
        private String sessionCode;
        private String title;
        private String description;
        private String imageUrl;
        private LocalDateTime createdAt;

        public static SessionCardDto from(Session session) {
            return SessionCardDto.builder()
                    .sessionId(session.getId())
                    .sessionCode(session.getSessionCode())
                    .title(session.getTitle())
                    .description(session.getDescription())
                    .imageUrl(session.getImageUrl())
                    .createdAt(session.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LikedQuestionDto {
        private Long questionId;
        private String sessionCode;
        private String sessionTitle;
        private String content;
        private int likeCount;

        public static LikedQuestionDto from(QuestionLike questionLike) {
            Question question = questionLike.getQuestion();
            return LikedQuestionDto.builder()
                    .questionId(question.getId())
                    .sessionCode(question.getSession().getSessionCode())
                    .sessionTitle(question.getSession().getTitle())
                    .content(question.getContent())
                    .likeCount(question.getLikeCount())
                    .build();
        }
    }
}
