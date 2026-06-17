package com.example.infomansys.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DependentDTO {

    private Integer depId;

    @NotBlank(message = "PIN ID (member reference) is required")
    private String pinId;

    @NotBlank(message = "Dependent name is required")
    private String dependentName;

    @NotBlank(message = "Relationship to member is required")
    private String relationshipToMember;

    @NotNull(message = "Date of birth is required")
    private LocalDate depDateOfBirth;

    @NotBlank(message = "Citizenship is required")
    @Pattern(regexp = "F|FN|DC", message = "Citizenship must be F, FN, or DC")
    private String depCitizenship;

    private Boolean depNoMiddleNameFlag;
    private Boolean depMononymFlag;
    private Boolean permanentDisabilityFlag;
}
