package com.example.mullkko.question.repository;

import com.example.mullkko.question.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionIdOrderByCreatedAtAsc(Long questionId);
}
