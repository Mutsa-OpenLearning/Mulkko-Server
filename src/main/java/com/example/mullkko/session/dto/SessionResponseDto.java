package com.example.mullkko.session.dto;

import com.example.mullkko.session.domain.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class SessionResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateSessionResponseDto {
        private Long sessionId;
        private String sessionCode;
        private String title;
        private String description;
        private String imageUrl;
        private LocalDateTime createdAt;

        public static CreateSessionResponseDto from(Session session) {
            return CreateSessionResponseDto.builder()
                    .sessionId(session.getId())
                    .sessionCode(session.getSessionCode())
                    .title(session.getTitle())
                    .description(session.getDescription())
                    .imageUrl(session.getImageUrl())
                    .createdAt(session.getCreatedAt())
                    .build();
        }
    }
}
