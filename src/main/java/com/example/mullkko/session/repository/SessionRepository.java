package com.example.mullkko.session.repository;

import com.example.mullkko.session.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findBySessionCode(String sessionCode);
    boolean existsBySessionCode(String sessionCode);
    List<Session> findByHostId(Long hostId);
}
