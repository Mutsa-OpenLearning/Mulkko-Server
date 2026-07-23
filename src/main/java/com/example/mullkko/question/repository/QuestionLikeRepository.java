package com.example.mullkko.question.repository;

import com.example.mullkko.question.domain.QuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Long> {
    boolean existsByQuestionIdAndUserId(Long questionId, Long userId);
    Optional<QuestionLike> findByQuestionIdAndUserId(Long questionId, Long userId);
    List<QuestionLike> findByUserId(Long userId);
}
