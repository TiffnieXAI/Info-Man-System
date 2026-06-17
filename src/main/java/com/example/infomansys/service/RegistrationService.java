package com.example.infomansys.service;

import com.example.infomansys.dto.RegistrationDTO;
import com.example.infomansys.entity.ContactInfo;
import com.example.infomansys.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final MemberService memberService;
    private final ContactInfoService contactInfoService;

    @Transactional
    public Map<String, Object> register(RegistrationDTO dto) {
        Member member = memberService.createMember(dto.getMember());

        ContactInfo contactInfo = contactInfoService.createContactInfo(
                member.getPinId(), dto.getContact());

        Map<String, Object> result = new HashMap<>();
        result.put("member", member);
        result.put("contactInfo", contactInfo);
        return result;
    }
}