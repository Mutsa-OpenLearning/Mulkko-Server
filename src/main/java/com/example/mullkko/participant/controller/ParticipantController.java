package com.example.mullkko.participant.controller;

import com.example.mullkko.global.apiPayload.GlobalResponse;
import com.example.mullkko.participant.dto.ParticipantRequestDto;
import com.example.mullkko.participant.dto.ParticipantResponseDto;
import com.example.mullkko.participant.service.ParticipantService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articipants")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;

    @PostMapping("/join")
    public ResponseEntity<ParticipantResponseDto.JoinResponseDto> joinSession(@RequestBody @Valid ParticipantRequestDto.JoinSessionRequestDto requestDto){
        ParticipantResponseDto.JoinResponseDto response =
                participantService.joinSession(requestDto);
        return GlobalResponse.onSuccess(response);

    }
}
