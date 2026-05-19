package com.ess.essserver.module.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String username;
    private String email;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
