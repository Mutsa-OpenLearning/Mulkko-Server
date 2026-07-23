package com.example.mullkko.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QuestionRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateQuestionDto {
        private String sessionCode;
        private Long userId;
        private String content;
        private Double positionX;
        private Double positionY;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateAnswerDto {
        private String sessionCode;
        private Long questionId;
        private Long hostId;
        private String content;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ToggleLikeDto {
        private String sessionCode;
        private Long questionId;
        private Long userId;
    }
}
