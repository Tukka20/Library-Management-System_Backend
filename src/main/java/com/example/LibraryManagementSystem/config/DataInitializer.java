package com.example.LibraryManagementSystem.config;


import com.example.LibraryManagementSystem.entity.Role;
import com.example.LibraryManagementSystem.enums.RoleName;
import com.example.LibraryManagementSystem.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void run(String... args) throws Exception {


        for (RoleName roleName:RoleName.values())
        {
            if (roleRepo.findByRoleName(roleName).isEmpty())
            {
                roleRepo.save(Role.builder()
                        .roleName(roleName)
                        .build());
            }
        }

    }
}
