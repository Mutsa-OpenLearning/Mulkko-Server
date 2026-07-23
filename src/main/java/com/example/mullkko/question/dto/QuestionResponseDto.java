package com.example.mullkko.question.dto;

import com.example.mullkko.question.domain.Answer;
import com.example.mullkko.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerInfoDto {
        private Long answerId;
        private String nickname;
        private String content;
        private LocalDateTime createdAt;

        public static AnswerInfoDto from(Answer answer) {
            return AnswerInfoDto.builder()
                    .answerId(answer.getId())
                    .nickname(answer.getNickname())
                    .content(answer.getContent())
                    .createdAt(answer.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionInfoDto {
        private Long questionId;
        private String sessionCode;
        private Long authorId;
        private String nickname;
        private String content;
        private Double positionX;
        private Double positionY;
        private int likeCount;
        private List<AnswerInfoDto> answers;
        private LocalDateTime createdAt;
        private String eventType;

        public static QuestionInfoDto from(Question question, String sessionCode, List<AnswerInfoDto> answers, String eventType) {
            return QuestionInfoDto.builder()
                    .questionId(question.getId())
                    .sessionCode(sessionCode)
                    .authorId(question.getAuthor().getId())
                    .nickname(question.getNickname())
                    .content(question.getContent())
                    .positionX(question.getPositionX())
                    .positionY(question.getPositionY())
                    .likeCount(question.getLikeCount())
                    .answers(answers)
                    .createdAt(question.getCreatedAt())
                    .eventType(eventType)
                    .build();
        }
    }
}
