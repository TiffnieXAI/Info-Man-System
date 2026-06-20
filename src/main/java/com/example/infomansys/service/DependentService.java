package com.example.infomansys.service;

import com.example.infomansys.dto.DependentDTO;
import com.example.infomansys.entity.Dependent;
import com.example.infomansys.entity.Member;
import com.example.infomansys.exception.ResourceNotFoundException;
import com.example.infomansys.repository.DependentRepository;
import com.example.infomansys.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DependentService {

    private final DependentRepository dependentRepository;
    private final MemberRepository memberRepository;

    // ── CREATE ────────────────────────────────────────────────────────────────
    @Transactional
    public Dependent createDependent(DependentDTO dto) {
        Member member = memberRepository.findById(dto.getPinId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Member not found with PIN ID: " + dto.getPinId()));

        Dependent dependent = toEntity(dto, member);
        return dependentRepository.save(dependent);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Dependent> getAllDependents() {
        return dependentRepository.findAll();
    }

    // ── READ BY MEMBER ────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Dependent> getDependentsByMember(String pinId) {
        if (!memberRepository.existsById(pinId)) {
            throw new ResourceNotFoundException(
                    "Member not found with PIN ID: " + pinId);
        }
        return dependentRepository.findByMember_PinId(pinId);
    }

    // ── READ ONE ──────────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public Dependent getDependentById(Integer depId) {
        return dependentRepository.findById(depId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Dependent not found with ID: " + depId));
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    @Transactional
    public Dependent updateDependent(Integer depId, DependentDTO dto) {
        Dependent existing = getDependentById(depId);

        Member member = memberRepository.findById(dto.getPinId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Member not found with PIN ID: " + dto.getPinId()));

        existing.setMember(member);
        existing.setDependentName(dto.getDependentName());
        existing.setRelationshipToMember(dto.getRelationshipToMember());
        existing.setDepDateOfBirth(dto.getDepDateOfBirth());
        existing.setDepCitizenship(dto.getDepCitizenship());
        existing.setDepNoMiddleNameFlag(dto.getDepNoMiddleNameFlag());
        existing.setDepMononymFlag(dto.getDepMononymFlag());
        existing.setPermanentDisabilityFlag(dto.getPermanentDisabilityFlag());

        return dependentRepository.save(existing);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    @Transactional
    public void deleteDependent(Integer depId) {
        if (!dependentRepository.existsById(depId)) {
            throw new ResourceNotFoundException(
                    "Dependent not found with ID: " + depId);
        }
        dependentRepository.deleteById(depId);
    }

    // ── DELETE ALL FOR A MEMBER ───────────────────────────────────────────────
    @Transactional
    public void deleteDependentsByMember(String pinId) {
        if (!memberRepository.existsById(pinId)) {
            throw new ResourceNotFoundException(
                    "Member not found with PIN ID: " + pinId);
        }
        dependentRepository.deleteByMember_PinId(pinId);
    }

    // ── MAPPER ────────────────────────────────────────────────────────────────
    private Dependent toEntity(DependentDTO dto, Member member) {
        return Dependent.builder()
                .member(member)
                .dependentName(dto.getDependentName())
                .relationshipToMember(dto.getRelationshipToMember())
                .depDateOfBirth(dto.getDepDateOfBirth())
                .depCitizenship(dto.getDepCitizenship())
                .depNoMiddleNameFlag(dto.getDepNoMiddleNameFlag())
                .depMononymFlag(dto.getDepMononymFlag())
                .permanentDisabilityFlag(dto.getPermanentDisabilityFlag())
                .build();
    }
}
