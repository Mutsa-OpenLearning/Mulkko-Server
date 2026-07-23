package com.example.mullkko.participant.domain;

import com.example.mullkko.session.domain.domain.Session;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "session_participants")
public class SessionParticipant {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

}
