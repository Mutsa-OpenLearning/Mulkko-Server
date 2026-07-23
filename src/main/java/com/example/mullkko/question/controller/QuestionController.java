package com.example.mullkko.question.controller;

import com.example.mullkko.question.config.QuestionApi;
import com.example.mullkko.question.dto.QuestionCreateRequestDto;
import com.example.mullkko.question.dto.QuestionResponseDto;
import com.example.mullkko.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController implements QuestionApi {

    private final QuestionService questionService;

    @Override
    public ResponseEntity<QuestionResponseDto> createQuestion(
            @PathVariable Long sessionId,
            @Valid @RequestBody QuestionCreateRequestDto request,
            Long userId
    ) {
        QuestionResponseDto response = questionService.createQuestion(sessionId, request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsBySession(@PathVariable Long sessionId) {
        List<QuestionResponseDto> response = questionService.getQuestionsBySession(sessionId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId, Long userId) {
        questionService.deleteQuestion(questionId, userId);
        return ResponseEntity.noContent().build();
    }
}
