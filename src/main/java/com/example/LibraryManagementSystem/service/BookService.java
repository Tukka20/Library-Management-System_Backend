package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.request.BookRequest;
import com.example.LibraryManagementSystem.dto.response.BookResponse;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.mapping.BookMapping;
import com.example.LibraryManagementSystem.repo.BookRepo;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public BookResponse createBook(BookRequest request)
    {
        if (bookRepo.existsByTitle(request.getTitle()))
        {
            throw new DuplicateRequestException("The book is already exists with title : "+request.getTitle());
        }

        Book book= BookMapping.mapBookRequestToEntity(request);

        Book savedBook=bookRepo.save(book);

        return BookMapping.mapEntityToDto(savedBook);
    }



    public BookResponse getBookByTitle(String title)
    {
        Book book=bookRepo.findByTitle(title)
                .orElseThrow(()->new ResourceNotFoundException("No book found with title : "+title));

        return BookMapping.mapEntityToDto(book);
    }


    public List<BookResponse> getAllBooks()
    {
        return bookRepo.findAll()
                .stream()
                .map(BookMapping::mapEntityToDto)
                .toList();
    }


    public BookResponse updateBook(String title, BookRequest request)
    {
        Book book=bookRepo.findByTitle(title)
                .orElseThrow(()->new ResourceNotFoundException("No book found with title : "+title));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setPrice(request.getPrice());
        book.setTotalCopies(request.getTotalCopies());

        Book updatedBook=bookRepo.save(book);

        return BookMapping.mapEntityToDto(updatedBook);
    }


    public void deleteBook(String title)
    {
        Book book=bookRepo.findByTitle(title)
                .orElseThrow(()->new ResourceNotFoundException("No book found with title : "+title));

        bookRepo.delete(book);



    }

}
