package com.EventPulse.entities;

import com.EventPulse.entities.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;


    @Column(nullable = false, length = 100)
    private String password;


    @Column(nullable = false, unique = true)
    @NotBlank
    private String userName;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
