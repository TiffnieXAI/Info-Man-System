package com.example.infomansys.service;

import com.example.infomansys.dto.ContactInfoDTO;
import com.example.infomansys.entity.ContactInfo;
import com.example.infomansys.entity.Member;
import com.example.infomansys.exception.DuplicateResourceException;
import com.example.infomansys.exception.ResourceNotFoundException;
import com.example.infomansys.repository.ContactInfoRepository;
import com.example.infomansys.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;
    private final MemberRepository memberRepository;

    // ── CREATE ────────────────────────────────────────────────────────────────
    @Transactional
    public ContactInfo createContactInfo(String pinId, ContactInfoDTO dto) {
        Member member = memberRepository.findById(pinId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Member not found with PIN ID: " + pinId));

        if (contactInfoRepository.existsById(pinId)) {
            throw new DuplicateResourceException(
                    "Contact info already exists for member with PIN ID: " + pinId);
        }

        ContactInfo contactInfo = toEntity(dto, member);
        return contactInfoRepository.save(contactInfo);
    }

    // ── READ ──────────────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public ContactInfo getContactInfoByMember(String pinId) {
        return contactInfoRepository.findByMember_PinId(pinId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contact info not found for member with PIN ID: " + pinId));
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    @Transactional
    public ContactInfo updateContactInfo(String pinId, ContactInfoDTO dto) {
        ContactInfo existing = getContactInfoByMember(pinId);

        existing.setPermanentHomeAddress(dto.getPermanentHomeAddress());
        existing.setMailAddress(dto.getMailAddress());
        existing.setHomeNumber(dto.getHomeNumber());
        existing.setMobileNumber(dto.getMobileNumber());
        existing.setBusinessNumber(dto.getBusinessNumber());
        existing.setEmailAddress(dto.getEmailAddress());

        return contactInfoRepository.save(existing);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    @Transactional
    public void deleteContactInfo(String pinId) {
        ContactInfo contactInfo = getContactInfoByMember(pinId);
        contactInfoRepository.delete(contactInfo);
    }

    // ── MAPPER ────────────────────────────────────────────────────────────────
    private ContactInfo toEntity(ContactInfoDTO dto, Member member) {
        return ContactInfo.builder()
                .member(member)
                .permanentHomeAddress(dto.getPermanentHomeAddress())
                .mailAddress(dto.getMailAddress())
                .homeNumber(dto.getHomeNumber())
                .mobileNumber(dto.getMobileNumber())
                .businessNumber(dto.getBusinessNumber())
                .emailAddress(dto.getEmailAddress())
                .build();
    }
}
