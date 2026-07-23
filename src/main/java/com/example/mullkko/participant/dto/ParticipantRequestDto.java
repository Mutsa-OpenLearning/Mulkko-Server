package com.example.mullkko.participant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ParticipantRequestDto {
    @Getter
    @NoArgsConstructor
    public static class JoinSessionRequestDto {
        @NotBlank(message = "PIN 코드는 필수 입력값입니다.")
        private String sessionCode;
        @NotNull(message = "회원 ID는 필수 입력값입니다.")
        private Long userId;
    }
}
