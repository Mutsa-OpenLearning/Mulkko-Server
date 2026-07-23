package com.example.mullkko.question.dto;

import com.example.mullkko.question.domain.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "질문 정보 응답 DTO")
public class QuestionResponseDto {

    @Schema(description = "질문 PK", example = "1")
    private Long questionId;

    @Schema(description = "세션 PK", example = "10")
    private Long sessionId;

    @Schema(description = "익명 닉네임", example = "물꼬 1234")
    private String anonymousNickname;

    @Schema(description = "질문 내용", example = "Spring Boot에서 JWT 만료 처리는 어떻게 구현하나요?")
    private String content;

    @Schema(description = "좋아요 수", example = "3")
    private Integer likeCount;

    @Schema(description = "답변 완료 여부", example = "false")
    private Boolean isAnswered;

    @Schema(description = "상단 고정 여부", example = "false")
    private Boolean isPinned;

    @Schema(description = "생성 일시", example = "2026-07-23T14:00:00")
    private LocalDateTime createdAt;

    public static QuestionResponseDto from(Question question) {
        return QuestionResponseDto.builder()
                .questionId(question.getId())
                .sessionId(question.getSession().getId())
                .anonymousNickname(question.getAnonymousNickname())
                .content(question.getContent())
                .likeCount(question.getLikeCount())
                .isAnswered(question.getIsAnswered())
                .isPinned(question.getIsPinned())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
