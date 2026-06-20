package com.example.infomansys.controller;

import com.example.infomansys.dto.ApiResponse;
import com.example.infomansys.dto.ContactInfoDTO;
import com.example.infomansys.entity.ContactInfo;
import com.example.infomansys.service.ContactInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactInfoController {

    private final ContactInfoService contactInfoService;

    // ADMIN only — handled by SecurityConfig
    @PostMapping("/{pinId}")
    public ResponseEntity<ApiResponse<ContactInfo>> createContactInfo(
            @PathVariable String pinId,
            @Valid @RequestBody ContactInfoDTO dto) {
        ContactInfo created = contactInfoService.createContactInfo(pinId, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Contact info created successfully.", created));
    }

    // ADMIN: any pinId | USER: own pinId only
    @GetMapping("/{pinId}")
    public ResponseEntity<ApiResponse<ContactInfo>> getContactInfo(
            @PathVariable String pinId) {
        ContactInfo contactInfo = contactInfoService.getContactInfoByMember(pinId);
        return ResponseEntity.ok(
                ApiResponse.success("Contact info retrieved successfully.", contactInfo));
    }

    // ADMIN: any pinId | USER: own pinId only
    @PutMapping("/{pinId}")
    public ResponseEntity<ApiResponse<ContactInfo>> updateContactInfo(
            @PathVariable String pinId,
            @Valid @RequestBody ContactInfoDTO dto) {
        ContactInfo updated = contactInfoService.updateContactInfo(pinId, dto);
        return ResponseEntity.ok(
                ApiResponse.success("Contact info updated successfully.", updated));
    }

    // ADMIN only — handled by SecurityConfig
    @DeleteMapping("/{pinId}")
    public ResponseEntity<ApiResponse<Void>> deleteContactInfo(
            @PathVariable String pinId) {
        contactInfoService.deleteContactInfo(pinId);
        return ResponseEntity.ok(
                ApiResponse.success("Contact info deleted successfully.", null));
    }
}