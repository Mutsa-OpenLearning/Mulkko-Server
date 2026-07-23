package com.example.mullkko.question.repository;

import com.example.mullkko.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySessionIdOrderByIsPinnedDescCreatedAtDesc(Long sessionId);
}
