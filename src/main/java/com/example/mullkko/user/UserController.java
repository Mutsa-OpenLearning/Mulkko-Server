package com.example.mullkko.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponseDto> getMyInfo(Long userId) {
        UserResponseDto response = userService.getMyInfo(userId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> withdraw(Long userId) {
        userService.withdraw(userId);
        return ResponseEntity.noContent().build();
    }
}