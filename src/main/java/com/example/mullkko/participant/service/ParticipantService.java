package com.example.mullkko.participant.service;

import com.example.mullkko.global.apiPayload.exception.ProjectException;
import com.example.mullkko.participant.code.ParticipantErrorCode;
import com.example.mullkko.participant.domain.SessionParticipant;
import com.example.mullkko.participant.repository.ParticipantRepository;
import com.example.mullkko.session.domain.Session;
import com.example.mullkko.session.repository.SessionRepository;
import com.example.mullkko.user.domain.User;
import com.example.mullkko.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @Transactional
    public Long joinSession(String sessionCode, Long userId){
        Session session = sessionRepository.findBySessionCode(sessionCode)
                .orElseThrow(() -> new ProjectException(ParticipantErrorCode.SESSION_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProjectException(ParticipantErrorCode.USER_NOT_FOUND));

        SessionParticipant participant = SessionParticipant
                .createSessionParticipant(session, user);

        participantRepository.save(participant);
        return session.getId();
    }




}
