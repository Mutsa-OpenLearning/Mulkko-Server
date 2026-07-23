package com.example.mullkko.user;

import com.example.mullkko.global.apiPayload.code.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User findOrCreateUser(String kakaoId) {
        return userRepository.findByKakaoId(kakaoId)
                .orElseGet(() -> userRepository.save(User.builder()
                        .kakaoId(kakaoId)
                        .build()));
    }

    public UserResponseDto getMyInfo(Long userId) {
        User user = findUserById(userId);
        return UserResponseDto.from(user);
    }

    @Transactional
    public void withdraw(Long userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(UserErrorCode.USER_NOT_FOUND.getMessage()));
    }
}