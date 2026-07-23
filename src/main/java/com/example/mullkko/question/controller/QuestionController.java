package com.example.mullkko.question.controller;

import com.example.mullkko.global.apiPayload.GlobalResponse;
import com.example.mullkko.question.dto.QuestionResponseDto;
import com.example.mullkko.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /**
     * [세션 방 질문 목록 조회 REST API]
     * GET /api/sessions/{sessionCode}/questions
     */
    @GetMapping("/{sessionCode}/questions")
    public GlobalResponse<List<QuestionResponseDto.QuestionInfoDto>> getQuestions(
            @PathVariable String sessionCode
    ) {
        List<QuestionResponseDto.QuestionInfoDto> response = questionService.getQuestionsBySessionCode(sessionCode);
        return GlobalResponse.onSuccess(response);
    }
}
