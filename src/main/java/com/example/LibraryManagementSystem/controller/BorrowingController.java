package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.request.BorrowingRequest;
import com.example.LibraryManagementSystem.dto.response.BorrowingResponse;
import com.example.LibraryManagementSystem.service.BorrowingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;


    @PostMapping("/user/{userName}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BorrowingResponse> borrowBook(@PathVariable String userName, @Valid @RequestBody BorrowingRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowingService.borrowBook(userName,request));

    }


    @PutMapping("/{borrowingId}/return/user/{userName}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BorrowingResponse> returnBook(@PathVariable Long borrowingId, @PathVariable String userName)
    {
        return ResponseEntity.ok(borrowingService.returnBook(borrowingId,userName));
    }




}
