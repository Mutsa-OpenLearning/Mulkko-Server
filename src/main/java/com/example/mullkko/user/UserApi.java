package com.example.mullkko.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "User API", description = "사용자 계정 관리 API")
public interface UserApi {

    @Operation(summary = "내 정보 조회", description = "JWT 토큰을 통해 로그인한 사용자의 기본 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "USER200", description = "조회 성공"),
            @ApiResponse(responseCode = "USER401", description = "인증 실패"),
            @ApiResponse(responseCode = "USER404", description = "사용자를 찾을 수 없음")
    })
    @GetMapping("/me")
    ResponseEntity<UserResponseDto> getMyInfo(
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId
    );

    @Operation(summary = "회원 탈퇴", description = "로그인한 사용자의 계정을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "USER204", description = "탈퇴 완료"),
            @ApiResponse(responseCode = "USER401", description = "인증 실패"),
            @ApiResponse(responseCode = "USER404", description = "사용자를 찾을 수 없음")
    })
    @DeleteMapping("/me")
    ResponseEntity<Void> withdraw(
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId
    );
}