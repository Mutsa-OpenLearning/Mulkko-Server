package com.example.mullkko.question.service;

import com.example.mullkko.global.apiPayload.code.UserErrorCode;
import com.example.mullkko.global.apiPayload.exception.ProjectException;
import com.example.mullkko.participant.code.ParticipantErrorCode;
import com.example.mullkko.question.code.QuestionErrorCode;
import com.example.mullkko.question.domain.Answer;
import com.example.mullkko.question.domain.Question;
import com.example.mullkko.question.domain.QuestionLike;
import com.example.mullkko.question.dto.QuestionRequestDto;
import com.example.mullkko.question.dto.QuestionResponseDto;
import com.example.mullkko.question.repository.AnswerRepository;
import com.example.mullkko.question.repository.QuestionLikeRepository;
import com.example.mullkko.question.repository.QuestionRepository;
import com.example.mullkko.session.domain.Session;
import com.example.mullkko.session.repository.SessionRepository;
import com.example.mullkko.user.domain.User;
import com.example.mullkko.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionLikeRepository questionLikeRepository;
    private final AnswerRepository answerRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public List<QuestionResponseDto.QuestionInfoDto> getQuestionsBySessionCode(String sessionCode) {
        Session session = sessionRepository.findBySessionCode(sessionCode)
                .orElseThrow(() -> new ProjectException(ParticipantErrorCode.SESSION_NOT_FOUND));

        List<Question> questions = questionRepository.findBySessionIdOrderByLikeCountDescCreatedAtAsc(session.getId());
        return questions.stream()
                .map(q -> {
                    List<QuestionResponseDto.AnswerInfoDto> answers = answerRepository.findByQuestionIdOrderByCreatedAtAsc(q.getId())
                            .stream().map(QuestionResponseDto.AnswerInfoDto::from).collect(Collectors.toList());
                    return QuestionResponseDto.QuestionInfoDto.from(q, sessionCode, answers, "INIT");
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public QuestionResponseDto.QuestionInfoDto createQuestion(QuestionRequestDto.CreateQuestionDto request) {
        Session session = sessionRepository.findBySessionCode(request.getSessionCode())
                .orElseThrow(() -> new ProjectException(ParticipantErrorCode.SESSION_NOT_FOUND));

        User author = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ProjectException(UserErrorCode.USER_NOT_FOUND));

        Question question = Question.createQuestion(session, author, request.getContent(), request.getPositionX(), request.getPositionY());
        questionRepository.save(question);

        return QuestionResponseDto.QuestionInfoDto.from(question, request.getSessionCode(), Collections.emptyList(), "NEW_QUESTION");
    }

    @Transactional
    public QuestionResponseDto.QuestionInfoDto createAnswer(QuestionRequestDto.CreateAnswerDto request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new ProjectException(QuestionErrorCode.QUESTION_NOT_FOUND));

        if (!question.getSession().getHost().getId().equals(request.getHostId())) {
            throw new ProjectException(UserErrorCode.UNAUTHORIZED_USER);
        }

        User host = userRepository.findById(request.getHostId())
                .orElseThrow(() -> new ProjectException(UserErrorCode.USER_NOT_FOUND));

        Answer answer = new Answer(question, host, request.getContent());
        answerRepository.save(answer);

        List<QuestionResponseDto.AnswerInfoDto> answers = answerRepository.findByQuestionIdOrderByCreatedAtAsc(question.getId())
                .stream().map(QuestionResponseDto.AnswerInfoDto::from).collect(Collectors.toList());

        return QuestionResponseDto.QuestionInfoDto.from(question, request.getSessionCode(), answers, "NEW_ANSWER");
    }

    @Transactional
    public QuestionResponseDto.QuestionInfoDto toggleLike(QuestionRequestDto.ToggleLikeDto request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new ProjectException(QuestionErrorCode.QUESTION_NOT_FOUND));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ProjectException(UserErrorCode.USER_NOT_FOUND));

        Optional<QuestionLike> existingLike = questionLikeRepository.findByQuestionIdAndUserId(question.getId(), user.getId());

        if (existingLike.isPresent()) {
            questionLikeRepository.delete(existingLike.get());
            question.decreaseLike();
        } else {
            QuestionLike newLike = new QuestionLike(question, user);
            questionLikeRepository.save(newLike);
            question.increaseLike();
        }

        List<QuestionResponseDto.AnswerInfoDto> answers = answerRepository.findByQuestionIdOrderByCreatedAtAsc(question.getId())
                .stream().map(QuestionResponseDto.AnswerInfoDto::from).collect(Collectors.toList());

        return QuestionResponseDto.QuestionInfoDto.from(question, request.getSessionCode(), answers, "TOGGLE_LIKE");
    }
}
