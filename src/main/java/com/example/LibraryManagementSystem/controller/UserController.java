package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.response.UserResponse;
import com.example.LibraryManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> getUserByUserName(@PathVariable String userName)
    {
        return ResponseEntity.ok(userService.getUserByUserName(userName));
    }


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUser()
    {

        return ResponseEntity.ok(userService.getAllUser());
    }


    @DeleteMapping("/{userName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName)
    {
        userService.deleteUser(userName);
        return ResponseEntity.noContent().build();

    }


}
