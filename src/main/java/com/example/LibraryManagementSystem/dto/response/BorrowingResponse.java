package com.example.LibraryManagementSystem.dto.response;

import com.example.LibraryManagementSystem.enums.BorrowingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingResponse {

    private Long id;

    private Long userId;
    private String userName;

    private Long bookId;
    private String title;

    private LocalDateTime borrowAt;
    private LocalDate dueDate;
    private LocalDateTime returnAt;

    private BorrowingStatus borrowingStatus;

}
