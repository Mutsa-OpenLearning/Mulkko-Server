package com.example.mullkko.session.code;

import com.example.mullkko.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SessionErrorCode implements BaseErrorCode {

    SESSION_NOT_FOUND(HttpStatus.NOT_FOUND, "SESSION404_1", "존재하지 않거나 유효하지 않은 세션입니다."),
    DUPLICATE_SESSION_CODE(HttpStatus.BAD_REQUEST, "SESSION400_1", "이미 존재하는 세션 코드입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
