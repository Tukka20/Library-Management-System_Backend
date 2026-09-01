package com.example.LibraryManagementSystem.entity;

import com.example.LibraryManagementSystem.enums.BorrowingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowingId;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column
    private LocalDateTime borrowAt;

    @Column
    private LocalDate dueDate;

    @Column
    private LocalDateTime returnAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BorrowingStatus borrowingStatus;

}
