package com.example.infomansys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "ContactInfoTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfo {

    @Id
    @Column(name = "pin_id", length = 12, nullable = false)
    private String pinId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "pin_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Member member;

    @Column(name = "permanent_home_address", length = 150, nullable = false)
    @NotBlank(message = "Permanent home address is required")
    private String permanentHomeAddress;

    @Column(name = "mail_address", length = 150)
    private String mailAddress;

    @Column(name = "home_number", length = 11)
    private String homeNumber;

    @Column(name = "mobile_number", length = 11, nullable = false)
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @Column(name = "business_number", length = 10)
    private String businessNumber;

    @Column(name = "email_address", length = 40)
    @Email(message = "Email must be valid")
    private String emailAddress;
}
