package com.example.infomansys.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UserTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // for USER role, links to their member record
    @Column(name = "pin_id")
    private String pinId;

    public enum Role {
        ADMIN, USER
    }
}