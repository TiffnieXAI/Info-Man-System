package com.example.infomansys.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DependentTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dependent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_id")
    private Integer depId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pin_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Member member;

    @Column(name = "dependent_name", length = 40, nullable = false)
    @NotBlank(message = "Dependent name is required")
    private String dependentName;

    @Column(name = "relationship_to_member", length = 20, nullable = false)
    @NotBlank(message = "Relationship to member is required")
    private String relationshipToMember;

    @Column(name = "dep_date_of_birth", nullable = false)
    @NotNull(message = "Dependent date of birth is required")
    private LocalDate depDateOfBirth;

    @Column(name = "dep_citizenship", length = 2, nullable = false)
    @Pattern(regexp = "F|FN|DC", message = "Citizenship must be F, FN, or DC")
    @NotBlank(message = "Citizenship is required")
    private String depCitizenship;

    @Column(name = "dep_no_middle_name_flag")
    private Boolean depNoMiddleNameFlag;

    @Column(name = "dep_mononym_flag")
    private Boolean depMononymFlag;

    @Column(name = "permanent_disability_flag")
    private Boolean permanentDisabilityFlag;
}
