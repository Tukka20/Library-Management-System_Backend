package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.request.BookRequest;
import com.example.LibraryManagementSystem.dto.response.BookResponse;
import com.example.LibraryManagementSystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request)
    {
        BookResponse response=bookService.createBook(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{title}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BookResponse> getBookByTitle(@PathVariable String title)
    {
        BookResponse response=bookService.getBookByTitle(title);

        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }




    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BookResponse>> getAllBooks()
    {
        return ResponseEntity.ok(bookService.getAllBooks());
    }


    @PatchMapping("/{title}")
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public ResponseEntity<BookResponse> updateBook(@PathVariable String title, @Valid @RequestBody BookRequest request )
    {
        return ResponseEntity.ok(bookService.updateBook(title,request));
    }


    @DeleteMapping("/{title}")
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public ResponseEntity<Void> deleteBook(@PathVariable String title)
    {
       bookService.deleteBook(title);

       return ResponseEntity.noContent().build();
    }
}
