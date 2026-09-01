package com.example.LibraryManagementSystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    @NotBlank(message = "Username is required")
    private String userName;


    @NotBlank(message = "Password is required")
    private String password;
}
