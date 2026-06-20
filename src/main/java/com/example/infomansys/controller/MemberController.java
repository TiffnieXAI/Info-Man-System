package com.example.infomansys.controller;

import com.example.infomansys.dto.ApiResponse;
import com.example.infomansys.dto.MemberDTO;
import com.example.infomansys.entity.Member;
import com.example.infomansys.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // ADMIN only — handled by SecurityConfig
    @PostMapping
    public ResponseEntity<ApiResponse<Member>> createMember(
            @Valid @RequestBody MemberDTO dto) {
        Member created = memberService.createMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Member registered successfully.", created));
    }

    // ADMIN only — handled by SecurityConfig
    @GetMapping
    public ResponseEntity<ApiResponse<List<Member>>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(
                ApiResponse.success("Members retrieved successfully.", members));
    }

    // ADMIN: any pinId | USER: own pinId only
    @GetMapping("/{pinId}")
    public ResponseEntity<ApiResponse<Member>> getMemberById(
            @PathVariable String pinId) {
        Member member = memberService.getMemberById(pinId);
        return ResponseEntity.ok(
                ApiResponse.success("Member retrieved successfully.", member));
    }

    // ADMIN: any pinId | USER: own pinId only
    @PutMapping("/{pinId}")
    public ResponseEntity<ApiResponse<Member>> updateMember(
            @PathVariable String pinId,
            @Valid @RequestBody MemberDTO dto) {
        Member updated = memberService.updateMember(pinId, dto);
        return ResponseEntity.ok(
                ApiResponse.success("Member updated successfully.", updated));
    }

    // ADMIN only — handled by SecurityConfig
    @DeleteMapping("/{pinId}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(
            @PathVariable String pinId) {
        memberService.deleteMember(pinId);
        return ResponseEntity.ok(
                ApiResponse.success("Member deleted successfully.", null));
    }
}