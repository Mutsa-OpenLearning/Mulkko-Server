package com.example.mullkko;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/me")
    public ResponseEntity<String> getMyInfo(@AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok("인증 성공! 현재 로그인한 User ID: " + userId);
    }
}
