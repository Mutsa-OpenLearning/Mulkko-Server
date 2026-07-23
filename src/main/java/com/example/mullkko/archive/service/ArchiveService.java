package com.example.mullkko.archive.service;

import com.example.mullkko.archive.dto.ArchiveResponseDto.LikedQuestionDto;
import com.example.mullkko.archive.dto.ArchiveResponseDto.SessionCardDto;
import com.example.mullkko.participant.domain.SessionParticipant;
import com.example.mullkko.participant.repository.ParticipantRepository;
import com.example.mullkko.question.domain.QuestionLike;
import com.example.mullkko.question.repository.QuestionLikeRepository;
import com.example.mullkko.session.domain.Session;
import com.example.mullkko.session.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArchiveService {

    private final ParticipantRepository participantRepository;
    private final SessionRepository sessionRepository;
    private final QuestionLikeRepository questionLikeRepository;

    public List<SessionCardDto> getMyParticipatedSessions(Long userId) {
        List<SessionParticipant> participants = participantRepository.findByUserId(userId);
        return participants.stream()
                .map(SessionParticipant::getSession)
                .map(SessionCardDto::from)
                .collect(Collectors.toList());
    }

    public List<SessionCardDto> getMyHostedSessions(Long userId) {
        List<Session> hostedSessions = sessionRepository.findByHostId(userId);
        return hostedSessions.stream()
                .map(SessionCardDto::from)
                .collect(Collectors.toList());
    }

    public List<LikedQuestionDto> getMyLikedQuestions(Long userId) {
        List<QuestionLike> likes = questionLikeRepository.findByUserId(userId);
        return likes.stream()
                .map(LikedQuestionDto::from)
                .collect(Collectors.toList());
    }
}
