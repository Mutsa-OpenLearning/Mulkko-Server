package com.example.mullkko.user.dto;

import com.example.mullkko.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "사용자 정보 응답 DTO")
public class UserResponseDto {

    @Schema(description = "사용자 고유 PK", example = "1")
    private Long userId;

    @Schema(description = "계정 생성 일시", example = "2026-07-23T12:00:00")
    private LocalDateTime createdAt;

    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }
}