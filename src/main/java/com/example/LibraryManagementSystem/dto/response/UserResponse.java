package com.example.LibraryManagementSystem.dto.response;

import com.example.LibraryManagementSystem.enums.RoleName;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private LocalDate dob;
    private RoleName roleName;
    private List<Long> borrowings;



}
