package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.request.UserLoginRequest;
import com.example.LibraryManagementSystem.dto.request.UserRegistrationRequest;
import com.example.LibraryManagementSystem.dto.response.AuthResponse;
import com.example.LibraryManagementSystem.dto.response.UserResponse;
import com.example.LibraryManagementSystem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> userRegistration(@Valid @RequestBody UserRegistrationRequest request)
    {
        UserResponse response=authService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @PostMapping("/login")
    public  ResponseEntity<AuthResponse> userLogin(@Valid @RequestBody UserLoginRequest request)
    {

        return ResponseEntity.ok(authService.loginUser(request));
    }
}
