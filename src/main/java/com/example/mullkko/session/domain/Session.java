package com.example.mullkko.session.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Session {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String sessionCode;
}
