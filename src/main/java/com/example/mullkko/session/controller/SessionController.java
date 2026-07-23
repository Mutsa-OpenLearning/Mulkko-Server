package com.example.mullkko.session.controller;

import com.example.mullkko.global.apiPayload.GlobalResponse;
import com.example.mullkko.session.dto.SessionRequestDto;
import com.example.mullkko.session.dto.SessionResponseDto;
import com.example.mullkko.session.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    /**
     * [세션 개설 API]
     * 요청: POST /api/sessions
     * Body: { "hostId": 1, "title": "Spring 수업", "description": "특강", "imageUrl": "..." }
     */
    @PostMapping
    public GlobalResponse<SessionResponseDto.CreateSessionResponseDto> createSession(
            @RequestBody @Valid SessionRequestDto.CreateSessionRequestDto request
    ) {
        SessionResponseDto.CreateSessionResponseDto response = sessionService.createSession(request);
        return GlobalResponse.onSuccess(response);
    }
}
