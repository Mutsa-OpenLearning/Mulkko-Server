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
        private String sessionTitle;
        private String message;

        public static JoinResponseDto from(Session session) {
            return JoinResponseDto.builder()
                    .sessionId(session.getId())
                    .sessionCode(session.getSessionCode())
                    .sessionTitle(session.getTitle())
                    .hostNickname(session.getHost() != null ? session.getHost().getNickname() : "발표자")
                    .status(session.getStatus() != null ? session.getStatus().name() : "ONGOING")
                    .build();
        }
    }
}
