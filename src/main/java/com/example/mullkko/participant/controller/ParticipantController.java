package com.example.mullkko.participant.controller;

import com.example.mullkko.global.apiPayload.GlobalResponse;
import com.example.mullkko.participant.dto.ParticipantRequestDto;
import com.example.mullkko.participant.dto.ParticipantResponseDto;
import com.example.mullkko.participant.service.ParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;

    @PostMapping("/join")
    public GlobalResponse<ParticipantResponseDto.JoinResponseDto> joinSession(@RequestBody @Valid ParticipantRequestDto.JoinSessionRequestDto requestDto){
        ParticipantResponseDto.JoinResponseDto response =
                participantService.joinSession(requestDto);
        return GlobalResponse.onSuccess(response);
    }
}

