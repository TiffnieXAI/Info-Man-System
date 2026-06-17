package com.example.infomansys.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "MemberTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @Column(name = "pin_id", length = 12, nullable = false)
    private String pinId;

    @Column(name = "purpose", length = 20, nullable = false)
    @NotBlank(message = "Purpose is required")
    private String purpose;

    @Column(name = "konsulta_provider", length = 100)
    private String konsultaProvider;

    @Column(name = "member_name", length = 40, nullable = false)
    @NotBlank(message = "Member name is required")
    private String memberName;

    @Column(name = "member_mother_name", length = 40, nullable = false)
    @NotBlank(message = "Mother's maiden name is required")
    private String memberMotherName;

    @Column(name = "member_spouse_name", length = 40)
    private String memberSpouseName;

    @Column(name = "date_of_birth", nullable = false)
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @Column(name = "birth_place", length = 100, nullable = false)
    @NotBlank(message = "Birth place is required")
    private String birthPlace;

    @Column(name = "sex", length = 1, nullable = false)
    @Pattern(regexp = "[MF]", message = "Sex must be M or F")
    @NotBlank(message = "Sex is required")
    private String sex;

    @Column(name = "civil_status", length = 2, nullable = false)
    @Pattern(regexp = "S|M|A|LS|W", message = "Civil status must be S, M, A, LS, or W")
    @NotBlank(message = "Civil status is required")
    private String civilStatus;

    @Column(name = "citizenship", length = 2, nullable = false)
    @Pattern(regexp = "F|FN|DC", message = "Citizenship must be F, FN, or DC")
    @NotBlank(message = "Citizenship is required")
    private String citizenship;

    @Column(name = "no_middle_name_flag")
    private Boolean noMiddleNameFlag;

    @Column(name = "mononym_flag")
    private Boolean mononymFlag;

    @Column(name = "PhilSysIdNum", length = 16)
    private String philSysIdNum;

    @Column(name = "tin_number", length = 12)
    private String tinNumber;

    @Column(name = "direct_contributor", length = 3)
    private String directContributor;

    @Column(name = "indirect_contributor", length = 3)
    private String indirectContributor;

    @Column(name = "profession", length = 35)
    private String profession;

    @Column(name = "income")
    private Double income;

    @Column(name = "proof_of_income", length = 60)
    private String proofOfIncome;

    // Relationships
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dependent> dependents;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private ContactInfo contactInfo;
}
