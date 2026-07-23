package com.example.mullkko.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "질문 생성 요청 DTO")
public class QuestionCreateRequestDto {

    @NotBlank(message = "질문 내용은 필수 입력 항목입니다.")
    @Schema(description = "질문 내용", example = "Spring Boot에서 JWT 만료 처리는 어떻게 구현하나요?")
    private String content;
}
