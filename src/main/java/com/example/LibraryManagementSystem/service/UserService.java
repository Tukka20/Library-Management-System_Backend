package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.response.UserResponse;
import com.example.LibraryManagementSystem.entity.User;
import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.mapping.UserMapping;
import com.example.LibraryManagementSystem.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public UserResponse getUserByUserName(String userName)
    {
        User user=userRepo.findByUserName(userName)
                .orElseThrow(()->new ResourceNotFoundException("No user found with username : "+userName));
        return UserMapping.mapEntityToResponse(user);
    }

    public List<UserResponse> getAllUser()
    {
        return userRepo.findAll()
                .stream()
                .map(UserMapping::mapEntityToResponse)
                .toList();
    }

    public void deleteUser(String userName)
    {
        User user=userRepo.findByUserName(userName)
                .orElseThrow(()->new ResourceNotFoundException("No user found with username : "+userName));

        userRepo.delete(user);
    }
}
