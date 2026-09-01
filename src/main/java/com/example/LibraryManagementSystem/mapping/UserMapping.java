package com.example.LibraryManagementSystem.mapping;

import com.example.LibraryManagementSystem.dto.request.UserRegistrationRequest;
import com.example.LibraryManagementSystem.dto.response.UserResponse;
import com.example.LibraryManagementSystem.entity.Borrowing;
import com.example.LibraryManagementSystem.entity.User;

import java.util.List;

public class UserMapping {


    private UserMapping()
    {

    }

    public static User mapUserRegistrationToEntity(UserRegistrationRequest request)
    {

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .email(request.getEmail())
                .dob(request.getDob())
                .build();
    }


    public static UserResponse mapEntityToResponse(User user)
    {
        return UserResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .dob(user.getDob())
                .roleName(user.getRole().getRoleName())
                .borrowings(user.getBorrowings() == null
                        ? List.of():user.getBorrowings()
                        .stream()
                        .map(Borrowing::getBorrowingId)
                        .toList()
                )
                .build();
    }

}
