package com.example.LibraryManagementSystem.mapping;

import com.example.LibraryManagementSystem.dto.response.BorrowingResponse;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.entity.Borrowing;
import com.example.LibraryManagementSystem.entity.User;
import com.example.LibraryManagementSystem.enums.BorrowingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BorrowingMapping {

    private BorrowingMapping()
    {

    }


    public static Borrowing mapBorrowingRequestToEntity(User user, Book book, LocalDate dueDate)
    {

        return Borrowing.builder()
                .user(user)
                .book(book)
                .borrowAt(LocalDateTime.now())
                .dueDate(dueDate)
                .returnAt(null)
                .borrowingStatus(BorrowingStatus.BORROWED)
                .build();
    }

    public static BorrowingResponse mapEntityToBorrowingResponse(Borrowing borrowing)
    {

        return  BorrowingResponse.builder()
                .id(borrowing.getBorrowingId())
                .userId(borrowing.getUser().getUserId())
                .userName(borrowing.getUser().getUserName())
                .bookId(borrowing.getBook().getBookId())
                .title(borrowing.getBook().getTitle())
                .borrowAt(borrowing.getBorrowAt())
                .dueDate(borrowing.getDueDate())
                .returnAt(borrowing.getReturnAt())
                .borrowingStatus(borrowing.getBorrowingStatus())
                .build();

    }
}
