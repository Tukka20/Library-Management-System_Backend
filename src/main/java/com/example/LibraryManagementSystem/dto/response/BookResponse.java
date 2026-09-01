package com.example.LibraryManagementSystem.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long bookId;
    private String title;
    private String author;
    private String description;
    private Integer totalCopies;
    private Integer availableCopies;
    private Double price;
    private List<Long> borrowings;

}
