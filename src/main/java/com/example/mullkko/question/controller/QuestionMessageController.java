package com.example.mullkko.question.controller;

import com.example.mullkko.question.dto.QuestionRequestDto;
import com.example.mullkko.question.dto.QuestionResponseDto;
import com.example.mullkko.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class QuestionMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final QuestionService questionService;

    /**
     * [실시간 질문 작성 STOMP 소켓 엔드포인트]
     * 프론트발행: /pub/questions
     * 구독자전송: /sub/sessions/{sessionCode}
     */
    @MessageMapping("/questions")
    public void sendQuestion(QuestionRequestDto.CreateQuestionDto request) {
        QuestionResponseDto.QuestionInfoDto response = questionService.createQuestion(request);
        messagingTemplate.convertAndSend("/sub/sessions/" + request.getSessionCode(), response);
    }

    /**
     * [실시간 발표자 답변 작성 STOMP 소켓 엔드포인트]
     * 프론트발행: /pub/answers
     * 구독자전송: /sub/sessions/{sessionCode}
     */
    @MessageMapping("/answers")
    public void sendAnswer(QuestionRequestDto.CreateAnswerDto request) {
        QuestionResponseDto.QuestionInfoDto response = questionService.createAnswer(request);
        messagingTemplate.convertAndSend("/sub/sessions/" + request.getSessionCode(), response);
    }

    /**
     * [실시간 질문 공감 토글 STOMP 소켓 엔드포인트]
     * 프론트발행: /pub/likes
     * 구독자전송: /sub/sessions/{sessionCode}
     */
    @MessageMapping("/likes")
    public void toggleLike(QuestionRequestDto.ToggleLikeDto request) {
        QuestionResponseDto.QuestionInfoDto response = questionService.toggleLike(request);
        messagingTemplate.convertAndSend("/sub/sessions/" + request.getSessionCode(), response);
    }
}
