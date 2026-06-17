package com.example.infomansys.controller;

import com.example.infomansys.dto.ApiResponse;
import com.example.infomansys.dto.DependentDTO;
import com.example.infomansys.entity.Dependent;
import com.example.infomansys.service.DependentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dependents")
@RequiredArgsConstructor
public class DependentController {

    private final DependentService dependentService;

    // POST /api/dependents
    @PostMapping
    public ResponseEntity<ApiResponse<Dependent>> createDependent(
            @Valid @RequestBody DependentDTO dto) {
        Dependent created = dependentService.createDependent(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Dependent added successfully.", created));
    }

    // GET /api/dependents
    @GetMapping
    public ResponseEntity<ApiResponse<List<Dependent>>> getAllDependents() {
        List<Dependent> dependents = dependentService.getAllDependents();
        return ResponseEntity.ok(
                ApiResponse.success("Dependents retrieved successfully.", dependents));
    }

    // GET /api/dependents/{depId}
    @GetMapping("/{depId}")
    public ResponseEntity<ApiResponse<Dependent>> getDependentById(
            @PathVariable Integer depId) {
        Dependent dependent = dependentService.getDependentById(depId);
        return ResponseEntity.ok(
                ApiResponse.success("Dependent retrieved successfully.", dependent));
    }

    // GET /api/dependents/member/{pinId}  — all dependents of one member
    @GetMapping("/member/{pinId}")
    public ResponseEntity<ApiResponse<List<Dependent>>> getDependentsByMember(
            @PathVariable String pinId) {
        List<Dependent> dependents = dependentService.getDependentsByMember(pinId);
        return ResponseEntity.ok(
                ApiResponse.success("Dependents for member retrieved successfully.", dependents));
    }

    // PUT /api/dependents/{depId}
    @PutMapping("/{depId}")
    public ResponseEntity<ApiResponse<Dependent>> updateDependent(
            @PathVariable Integer depId,
            @Valid @RequestBody DependentDTO dto) {
        Dependent updated = dependentService.updateDependent(depId, dto);
        return ResponseEntity.ok(
                ApiResponse.success("Dependent updated successfully.", updated));
    }

    // DELETE /api/dependents/{depId}
    @DeleteMapping("/{depId}")
    public ResponseEntity<ApiResponse<Void>> deleteDependent(
            @PathVariable Integer depId) {
        dependentService.deleteDependent(depId);
        return ResponseEntity.ok(
                ApiResponse.success("Dependent deleted successfully.", null));
    }
}
