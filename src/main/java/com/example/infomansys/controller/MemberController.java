package com.example.infomansys.controller;

import com.example.infomansys.dto.ApiResponse;
import com.example.infomansys.dto.MemberDTO;
import com.example.infomansys.dto.RegistrationDTO;
import com.example.infomansys.entity.ContactInfo;
import com.example.infomansys.entity.Member;
import com.example.infomansys.service.ContactInfoService;
import com.example.infomansys.service.DependentService;
import com.example.infomansys.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ContactInfoService contactInfoService;
    private final DependentService dependentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Member>> createMember(
            @Valid @RequestBody MemberDTO dto) {
        Member created = memberService.createMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Member registered successfully.", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getAllMembers() {
        List<Map<String, Object>> members = memberService.getAllMembersComposed();
        return ResponseEntity.ok(
                ApiResponse.success("Members retrieved successfully.", members));
    }

    @GetMapping("/{pinId}")
    public ResponseEntity<ApiResponse<Member>> getMemberById(
            @PathVariable String pinId) {
        Member member = memberService.getMemberById(pinId);
        return ResponseEntity.ok(
                ApiResponse.success("Member retrieved successfully.", member));
    }

    // admin.html sends { member: {...}, contact: {...} } in one PUT, so this
    // endpoint accepts that combined shape and updates both records.
    @PutMapping("/{pinId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateMember(
            @PathVariable String pinId,
            @Valid @RequestBody RegistrationDTO dto) {
        Member updatedMember = memberService.updateMember(pinId, dto.getMember());
        ContactInfo updatedContact = contactInfoService.updateContactInfo(pinId, dto.getContact());

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("member", updatedMember);
        result.put("contactInfo", updatedContact);

        return ResponseEntity.ok(
                ApiResponse.success("Member updated successfully.", result));
    }

    @DeleteMapping("/{pinId}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(
            @PathVariable String pinId) {
        memberService.deleteMember(pinId);
        return ResponseEntity.ok(
                ApiResponse.success("Member deleted successfully.", null));
    }

    // admin.html clears out a member's dependents before re-adding the edited set
    @DeleteMapping("/{pinId}/dependents")
    public ResponseEntity<ApiResponse<Void>> deleteMemberDependents(
            @PathVariable String pinId) {
        dependentService.deleteDependentsByMember(pinId);
        return ResponseEntity.ok(
                ApiResponse.success("Dependents cleared successfully.", null));
    }
}