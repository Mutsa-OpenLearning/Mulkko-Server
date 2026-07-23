package com.example.mullkko.participant.code;

import com.example.mullkko.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ParticipantErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "PARTICIPANT404_1", "존재하지 않는 회원입니다."),
    SESSION_NOT_FOUND(HttpStatus.NOT_FOUND, "PARTICIPANT404_2", "존재하지 않는 세션입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
