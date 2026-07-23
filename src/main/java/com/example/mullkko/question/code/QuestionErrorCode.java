package com.example.mullkko.question.code;

import com.example.mullkko.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum QuestionErrorCode implements BaseErrorCode {

    // 400 Bad Request
    INVALID_QUESTION_CONTENT(HttpStatus.BAD_REQUEST, "QUESTION400", "질문 내용은 비어있을 수 없습니다."),

    // 403 Forbidden
    QUESTION_ACCESS_DENIED(HttpStatus.FORBIDDEN, "QUESTION403", "해당 질문에 대한 접근/삭제 권한이 없습니다."),

    // 404 Not Found
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "QUESTION404", "존재하지 않는 질문입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}