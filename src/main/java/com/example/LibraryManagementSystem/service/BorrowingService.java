package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.request.BorrowingRequest;
import com.example.LibraryManagementSystem.dto.response.BorrowingResponse;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.entity.Borrowing;
import com.example.LibraryManagementSystem.entity.User;
import com.example.LibraryManagementSystem.enums.BorrowingStatus;
import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.mapping.BorrowingMapping;
import com.example.LibraryManagementSystem.repo.BookRepo;
import com.example.LibraryManagementSystem.repo.BorrowingRepo;
import com.example.LibraryManagementSystem.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepo borrowingRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    @Transactional
    public BorrowingResponse borrowBook(String userName, BorrowingRequest request)
    {

        User user=userRepo.findByUserName(userName)
                .orElseThrow(()->new ResourceNotFoundException("No user found with username : "+userName));


        Book book=bookRepo.findByTitle(request.getTitle())
                .orElseThrow(()->new ResourceNotFoundException("No book found with title : "+request.getTitle()));

        if (book.getAvailableCopies()<=0)
        {
            throw new IllegalStateException("No available copies for this book");
        }

        LocalDate dueDate=LocalDate.now().plusDays(15);

        Borrowing borrowing= BorrowingMapping.mapBorrowingRequestToEntity(user,book,dueDate);

        book.setAvailableCopies(book.getAvailableCopies()-1);

        Borrowing savedBorrowing=borrowingRepo.save(borrowing);

        return BorrowingMapping.mapEntityToBorrowingResponse(savedBorrowing);

    }


    @Transactional
    public BorrowingResponse returnBook(Long borrowingId,String userName)
    {

        Borrowing borrowing=borrowingRepo.findByBorrowingIdAndUser_UserName(borrowingId,userName)
                .orElseThrow(()->new ResourceNotFoundException("Borrowing not found with this username : "+userName));

        if(borrowing.getBorrowingStatus()!= BorrowingStatus.BORROWED)
        {
            throw new IllegalStateException("Book already been returned");
        }

        borrowing.setReturnAt(LocalDateTime.now());
        borrowing.setBorrowingStatus(BorrowingStatus.RETURNED);

        Book book=borrowing.getBook();

        book.setAvailableCopies(book.getAvailableCopies()+1);

        bookRepo.save(book);

        Borrowing updatedBorrowing=borrowingRepo.save(borrowing);

        return BorrowingMapping.mapEntityToBorrowingResponse(updatedBorrowing);

    }

}
