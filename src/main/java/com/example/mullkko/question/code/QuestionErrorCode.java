package com.example.mullkko.question.code;

import com.example.mullkko.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum QuestionErrorCode implements BaseErrorCode {

    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "QUESTION404_1", "존재하지 않는 질문입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
