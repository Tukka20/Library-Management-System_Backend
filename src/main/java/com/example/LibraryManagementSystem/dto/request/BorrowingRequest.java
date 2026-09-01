package com.example.LibraryManagementSystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRequest {

    @NotBlank(message = "Book title is required")
    private String title;
}
