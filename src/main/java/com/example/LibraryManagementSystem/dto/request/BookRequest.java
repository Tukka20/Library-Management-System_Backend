package com.example.LibraryManagementSystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author Name is required")
    private String author;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Total copies is required")
    @Positive(message = "Total copies must be greater than 0")
    private Integer totalCopies;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

}
