package com.example.mullkko.participant.repository;

import com.example.mullkko.participant.domain.SessionParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<SessionParticipant, Long> {
    List<SessionParticipant> findBySessionId(Long sessionId);
    List<SessionParticipant> findByUserId(Long userId);
    boolean existsBySessionIdAndUserId(Long sessionId, Long userId);
}
