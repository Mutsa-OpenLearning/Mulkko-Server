package com.example.mullkko.question.config;

import com.example.mullkko.question.dto.QuestionCreateRequestDto;
import com.example.mullkko.question.dto.QuestionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Question API", description = "실시간 질문 관리 API")
public interface QuestionApi {

    @Operation(summary = "질문 등록", description = "특정 세션에 새로운 질문을 등록합니다. 익명 닉네임이 자동 생성됩니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "QUESTION201", description = "질문 등록 성공"),
            @ApiResponse(responseCode = "QUESTION400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "QUESTION401", description = "인증 실패"),
            @ApiResponse(responseCode = "QUESTION404", description = "세션을 찾을 수 없음")
    })
    @PostMapping("/api/sessions/{sessionId}/questions")
    ResponseEntity<QuestionResponseDto> createQuestion(
            @PathVariable Long sessionId,
            @Valid @RequestBody QuestionCreateRequestDto request,
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId
    );

    @Operation(summary = "세션별 질문 목록 조회", description = "특정 세션의 전체 질문 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "QUESTION200", description = "질문 목록 조회 성공"),
            @ApiResponse(responseCode = "QUESTION404", description = "세션을 찾을 수 없음")
    })
    @GetMapping("/api/sessions/{sessionId}/questions")
    ResponseEntity<List<QuestionResponseDto>> getQuestionsBySession(
            @PathVariable Long sessionId
    );

    @Operation(summary = "질문 삭제", description = "질문 작성자가 작성한 질문을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "QUESTION204", description = "질문 삭제 성공"),
            @ApiResponse(responseCode = "QUESTION401", description = "인증 실패"),
            @ApiResponse(responseCode = "QUESTION403", description = "삭제 권한 없음"),
            @ApiResponse(responseCode = "QUESTION404", description = "질문을 찾을 수 없음")
    })
    @DeleteMapping("/api/questions/{questionId}")
    ResponseEntity<Void> deleteQuestion(
            @PathVariable Long questionId,
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId
    );
}