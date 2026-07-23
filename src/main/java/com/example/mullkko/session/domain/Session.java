package com.example.mullkko.session.domain;

import jakarta.persistence.*;

@Entity
public class Session {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String sessionCode;
}
