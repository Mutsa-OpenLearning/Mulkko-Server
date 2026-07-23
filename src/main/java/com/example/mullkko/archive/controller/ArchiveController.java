package com.example.mullkko.archive.controller;

import com.example.mullkko.archive.dto.ArchiveResponseDto.LikedQuestionDto;
import com.example.mullkko.archive.dto.ArchiveResponseDto.SessionCardDto;
import com.example.mullkko.archive.service.ArchiveService;
import com.example.mullkko.global.apiPayload.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class ArchiveController {

    private final ArchiveService archiveService;

    @GetMapping("/participated-sessions")
    public GlobalResponse<List<SessionCardDto>> getMyParticipatedSessions(@RequestParam Long userId) {
        List<SessionCardDto> response = archiveService.getMyParticipatedSessions(userId);
        return GlobalResponse.onSuccess(response);
    }

    @GetMapping("/hosted-sessions")
    public GlobalResponse<List<SessionCardDto>> getMyHostedSessions(@RequestParam Long userId) {
        List<SessionCardDto> response = archiveService.getMyHostedSessions(userId);
        return GlobalResponse.onSuccess(response);
    }

    @GetMapping("/liked-questions")
    public GlobalResponse<List<LikedQuestionDto>> getMyLikedQuestions(@RequestParam Long userId) {
        List<LikedQuestionDto> response = archiveService.getMyLikedQuestions(userId);
        return GlobalResponse.onSuccess(response);
    }
}
