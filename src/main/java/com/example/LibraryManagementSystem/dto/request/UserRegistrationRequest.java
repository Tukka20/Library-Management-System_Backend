package com.example.LibraryManagementSystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "User name is required")
    private String userName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email id is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 7,message = "Password must contain at least 7 character")
    private String password;

    @NotNull(message = "Date of Birth is required")
    private LocalDate dob;


}
