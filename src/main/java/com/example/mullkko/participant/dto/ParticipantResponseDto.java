package com.example.mullkko.participant.dto;

import com.example.mullkko.session.domain.Session;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ParticipantResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResponseDto {
        private Long sessionId;
        private String sessionCode;
        private String sessionTitle;
        private String description;
        private String imageUrl;

        public static JoinResponseDto from(Session session) {
            return JoinResponseDto.builder()
                    .sessionId(session.getId())
                    .sessionCode(session.getSessionCode())
                    .sessionTitle(session.getTitle())
                    .description(session.getDescription())
                    .imageUrl(session.getImageUrl())
                    .build();
        }
    }
}
