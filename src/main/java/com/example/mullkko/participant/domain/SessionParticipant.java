package com.example.mullkko.participant.domain;

import com.example.mullkko.session.domain.Session;
import com.example.mullkko.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "session_participants")
public class SessionParticipant {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime joinedAt = LocalDateTime.now();

    private SessionParticipant(Session session, User user){
        this.session = session;
        this.user = user;
        this.joinedAt = LocalDateTime.now();
    }

    public static SessionParticipant createSessionParticipant(Session session, User user){
        return new SessionParticipant(session, user);
    }
}
