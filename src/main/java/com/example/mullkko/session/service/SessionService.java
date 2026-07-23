package com.example.mullkko.session.service;

import com.example.mullkko.global.apiPayload.code.UserErrorCode;
import com.example.mullkko.global.apiPayload.exception.ProjectException;
import com.example.mullkko.session.domain.Session;
import com.example.mullkko.session.dto.SessionRequestDto;
import com.example.mullkko.session.dto.SessionResponseDto;
import com.example.mullkko.session.repository.SessionRepository;

import com.example.mullkko.user.domain.User;
import com.example.mullkko.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Transactional
    public SessionResponseDto.CreateSessionResponseDto createSession(SessionRequestDto.CreateSessionRequestDto request) {
        // 1. 세션을 개설하는 호스트 회원 존재 여부 검증
        User host = userRepository.findById(request.getHostId())
                .orElseThrow(() -> new ProjectException(UserErrorCode.USER_NOT_FOUND));

        // 2. 중복 없는 6자리 숫자 PIN 코드 생성 (예: "849201")
        String sessionCode = generateUniqueSessionCode();

        // 3. 세션 엔티티 생성 및 DB 저장
        Session session = Session.createSession(
                host,
                request.getTitle(),
                request.getDescription(),
                request.getImageUrl(),
                sessionCode
        );
        sessionRepository.save(session);

        // 4. 응답 DTO 변환 및 리턴
        return SessionResponseDto.CreateSessionResponseDto.from(session);
    }

    // 6자리 숫자 PIN 코드 생성 메서드 (100000 ~ 999999)
    private String generateUniqueSessionCode() {
        String code;
        do {
            int randomNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);
            code = String.valueOf(randomNumber);
        } while (sessionRepository.existsBySessionCode(code));
        return code;
    }
}
