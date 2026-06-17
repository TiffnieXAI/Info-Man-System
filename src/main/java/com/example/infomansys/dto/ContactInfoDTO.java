package com.example.infomansys.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfoDTO {

    @NotBlank(message = "Permanent home address is required")
    private String permanentHomeAddress;

    private String mailAddress;
    private String homeNumber;

    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    private String businessNumber;

    @Email(message = "Email must be valid")
    private String emailAddress;
}