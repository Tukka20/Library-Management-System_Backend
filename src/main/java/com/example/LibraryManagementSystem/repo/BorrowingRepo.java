package com.example.LibraryManagementSystem.repo;

import com.example.LibraryManagementSystem.entity.Borrowing;
import com.example.LibraryManagementSystem.enums.BorrowingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepo extends JpaRepository<Borrowing,Long> {

    List<Borrowing> findByUser_UserId(Long userId);

    List<Borrowing> findByBook_BookId(Long bookId);

    Optional<Borrowing> findByBorrowingIdAndUser_UserName(Long borrowingId,String userName);

    boolean existsByUser_UserIdAndBook_BookIdAndBorrowingStatus(Long userId, Long bookId, BorrowingStatus borrowingStatus);


}
