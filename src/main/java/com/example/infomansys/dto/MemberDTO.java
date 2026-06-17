package com.example.infomansys.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    @NotBlank(message = "PIN ID is required")
    @Size(max = 12, message = "PIN ID must not exceed 12 characters")
    private String pinId;

    @NotBlank(message = "Purpose is required")
    private String purpose;

    private String konsultaProvider;

    @NotBlank(message = "Member name is required")
    private String memberName;

    @NotBlank(message = "Mother's maiden name is required")
    private String memberMotherName;

    private String memberSpouseName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Birth place is required")
    private String birthPlace;

    @NotBlank(message = "Sex is required")
    @Pattern(regexp = "[MF]", message = "Sex must be M or F")
    private String sex;

    @NotBlank(message = "Civil status is required")
    @Pattern(regexp = "S|M|A|LS|W", message = "Civil status must be S, M, A, LS, or W")
    private String civilStatus;

    @NotBlank(message = "Citizenship is required")
    @Pattern(regexp = "F|FN|DC", message = "Citizenship must be F, FN, or DC")
    private String citizenship;

    private Boolean noMiddleNameFlag;
    private Boolean mononymFlag;
    private String philSysIdNum;
    private String tinNumber;
    private String directContributor;
    private String indirectContributor;
    private String profession;
    private Double income;
    private String proofOfIncome;
}
