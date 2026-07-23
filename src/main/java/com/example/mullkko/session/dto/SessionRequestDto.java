package com.example.mullkko.session.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SessionRequestDto {

    @Getter
    @NoArgsConstructor
    public static class CreateSessionRequestDto {

        @NotNull(message = "호스트 ID는 필수 입력값입니다.")
        private Long hostId;

        @NotBlank(message = "세션 제목은 필수 입력값입니다.")
        private String title;

        private String description;
        private String imageUrl;
    }
}
