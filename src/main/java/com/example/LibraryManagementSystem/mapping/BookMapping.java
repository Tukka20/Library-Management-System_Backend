package com.example.LibraryManagementSystem.mapping;

import com.example.LibraryManagementSystem.dto.request.BookRequest;
import com.example.LibraryManagementSystem.dto.response.BookResponse;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.entity.Borrowing;

import java.util.List;

public class BookMapping {


    private BookMapping()
    {

    }


    public static Book mapBookRequestToEntity(BookRequest request)
    {

        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .description(request.getDescription())
                .price(request.getPrice())
                .totalCopies(request.getTotalCopies())
                .availableCopies(request.getTotalCopies())
                .build();
    }

    public static BookResponse mapEntityToDto(Book book)
    {

        return BookResponse.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .price(book.getPrice())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .borrowings(
                        book.getBorrowings() == null
                                ? List.of()
                            :book.getBorrowings()
                        .stream()
                        .map(Borrowing::getBorrowingId)
                        .toList()
                )
                .build();
    }
}
