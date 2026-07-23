package com.example.mullkko.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    // 400 Bad Request
    INVALID_KAKAO_TOKEN(HttpStatus.BAD_REQUEST, "USER400", "유효하지 않은 카카오 토큰입니다."),

    // 401 Unauthorized
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "USER401_1", "인증 정보가 유효하지 않습니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "USER401_2", "만료된 JWT 토큰입니다."),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "USER401_3", "손상되거나 지원하지 않는 JWT 토큰입니다."),

    // 404 Not Found
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "존재하지 않는 사용자입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
