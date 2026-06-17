package com.example.infomansys.service;

import com.example.infomansys.dto.MemberDTO;
import com.example.infomansys.entity.Member;
import com.example.infomansys.exception.DuplicateResourceException;
import com.example.infomansys.exception.ResourceNotFoundException;
import com.example.infomansys.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // ── CREATE ────────────────────────────────────────────────────────────────
    @Transactional
    public Member createMember(MemberDTO dto) {
        if (memberRepository.existsByPinId(dto.getPinId())) {
            throw new DuplicateResourceException(
                    "Member with PIN ID '" + dto.getPinId() + "' already exists.");
        }
        Member member = toEntity(dto);
        return memberRepository.save(member);
    }

    // ── READ ALL ──────────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // ── READ ONE ──────────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public Member getMemberById(String pinId) {
        return memberRepository.findById(pinId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Member not found with PIN ID: " + pinId));
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    @Transactional
    public Member updateMember(String pinId, MemberDTO dto) {
        Member existing = getMemberById(pinId);

        existing.setPurpose(dto.getPurpose());
        existing.setKonsultaProvider(dto.getKonsultaProvider());
        existing.setMemberName(dto.getMemberName());
        existing.setMemberMotherName(dto.getMemberMotherName());
        existing.setMemberSpouseName(dto.getMemberSpouseName());
        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setBirthPlace(dto.getBirthPlace());
        existing.setSex(dto.getSex());
        existing.setCivilStatus(dto.getCivilStatus());
        existing.setCitizenship(dto.getCitizenship());
        existing.setNoMiddleNameFlag(dto.getNoMiddleNameFlag());
        existing.setMononymFlag(dto.getMononymFlag());
        existing.setPhilSysIdNum(dto.getPhilSysIdNum());
        existing.setTinNumber(dto.getTinNumber());
        existing.setDirectContributor(dto.getDirectContributor());
        existing.setIndirectContributor(dto.getIndirectContributor());
        existing.setProfession(dto.getProfession());
        existing.setIncome(dto.getIncome());
        existing.setProofOfIncome(dto.getProofOfIncome());

        return memberRepository.save(existing);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    @Transactional
    public void deleteMember(String pinId) {
        if (!memberRepository.existsByPinId(pinId)) {
            throw new ResourceNotFoundException(
                    "Member not found with PIN ID: " + pinId);
        }
        memberRepository.deleteById(pinId);
    }

    // ── MAPPER ────────────────────────────────────────────────────────────────
    private Member toEntity(MemberDTO dto) {
        return Member.builder()
                .pinId(dto.getPinId())
                .purpose(dto.getPurpose())
                .konsultaProvider(dto.getKonsultaProvider())
                .memberName(dto.getMemberName())
                .memberMotherName(dto.getMemberMotherName())
                .memberSpouseName(dto.getMemberSpouseName())
                .dateOfBirth(dto.getDateOfBirth())
                .birthPlace(dto.getBirthPlace())
                .sex(dto.getSex())
                .civilStatus(dto.getCivilStatus())
                .citizenship(dto.getCitizenship())
                .noMiddleNameFlag(dto.getNoMiddleNameFlag())
                .mononymFlag(dto.getMononymFlag())
                .philSysIdNum(dto.getPhilSysIdNum())
                .tinNumber(dto.getTinNumber())
                .directContributor(dto.getDirectContributor())
                .indirectContributor(dto.getIndirectContributor())
                .profession(dto.getProfession())
                .income(dto.getIncome())
                .proofOfIncome(dto.getProofOfIncome())
                .build();
    }
}
