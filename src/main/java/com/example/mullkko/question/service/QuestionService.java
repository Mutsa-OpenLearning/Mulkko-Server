package com.example.mullkko.question.service;

import com.example.mullkko.global.apiPayload.code.UserErrorCode;
import com.example.mullkko.global.apiPayload.exception.ProjectException;
import com.example.mullkko.question.code.QuestionErrorCode;
import com.example.mullkko.question.domain.Question;
import com.example.mullkko.question.dto.QuestionCreateRequestDto;
import com.example.mullkko.question.dto.QuestionResponseDto;
import com.example.mullkko.question.repository.QuestionRepository;
import com.example.mullkko.session.code.SessionErrorCode;
import com.example.mullkko.session.domain.Session;
import com.example.mullkko.session.repository.SessionRepository;
import com.example.mullkko.user.domain.User;
import com.example.mullkko.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Transactional
    public QuestionResponseDto createQuestion(Long sessionId, QuestionCreateRequestDto request, Long userId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ProjectException((SessionErrorCode.SESSION_NOT_FOUND)));

        User author = userRepository.findById(userId)
                .orElseThrow(() -> new ProjectException(UserErrorCode.USER_NOT_FOUND));

        Question question = Question.builder()
                .session(session)
                .author(author)
                .anonymousNickname(author.getNickname())
                .content(request.getContent())
                .build();

        Question savedQuestion = questionRepository.save(question);
        return QuestionResponseDto.from(savedQuestion);
    }

    public List<QuestionResponseDto> getQuestionsBySession(Long sessionId) {
        return questionRepository.findBySessionIdOrderByIsPinnedDescCreatedAtDesc(sessionId)
                .stream()
                .map(QuestionResponseDto::from)
                .toList();
    }

    @Transactional
    public void deleteQuestion(Long questionId, Long userId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ProjectException(QuestionErrorCode.QUESTION_NOT_FOUND));

        if (!question.getAuthor().getId().equals(userId)) {
            throw new ProjectException(QuestionErrorCode.QUESTION_ACCESS_DENIED);
        }

        questionRepository.delete(question);
    }
}