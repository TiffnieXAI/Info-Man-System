package com.example.infomansys.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDTO {

    @NotNull(message = "Member details are required")
    @Valid
    private MemberDTO member;

    @NotNull(message = "Contact info is required")
    @Valid
    private ContactInfoDTO contact;
}