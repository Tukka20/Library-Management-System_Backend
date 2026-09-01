package com.example.LibraryManagementSystem.service;


import com.example.LibraryManagementSystem.dto.request.UserLoginRequest;
import com.example.LibraryManagementSystem.dto.request.UserRegistrationRequest;
import com.example.LibraryManagementSystem.dto.response.AuthResponse;
import com.example.LibraryManagementSystem.dto.response.UserResponse;
import com.example.LibraryManagementSystem.entity.Role;
import com.example.LibraryManagementSystem.entity.User;
import com.example.LibraryManagementSystem.enums.RoleName;
import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.mapping.UserMapping;
import com.example.LibraryManagementSystem.repo.RoleRepo;
import com.example.LibraryManagementSystem.repo.UserRepo;
import com.example.LibraryManagementSystem.util.JwtUtil;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public UserResponse registerUser(UserRegistrationRequest request)
    {

        //check username
        if (userRepo.existsByUserName(request.getUserName()))
        {
            throw new DuplicateRequestException("User already exists with username");
        }

        //check email
        if (userRepo.existsByEmail(request.getEmail()))
        {
            throw new DuplicateRequestException("Email id already exists");
        }

        //map request to entity
        User user= UserMapping.mapUserRegistrationToEntity(request);

        //Encode password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Assign default USER role
        Role role=roleRepo.findByRoleName(RoleName.USER)
                .orElseThrow(()->new ResourceNotFoundException("User role not found"));

        user.setRole(role);

        //save user
        User saveUser=userRepo.save(user);

        return UserMapping.mapEntityToResponse(saveUser);

    }



    public AuthResponse loginUser(UserLoginRequest request)
    {

        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUserName(),
                request.getPassword()
        ));

        String token= jwtUtil.generateToken(authentication.getName());

        return AuthResponse.builder()
                .token(token)
                .build();
    }

}
