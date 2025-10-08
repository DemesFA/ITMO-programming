package com.rafael.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserModel {
    private String username;
    private String name;
    private String password;
    private String role;
    private LocalDate birthday;
}
