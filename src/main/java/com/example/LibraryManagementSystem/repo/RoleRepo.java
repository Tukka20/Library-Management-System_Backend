package com.example.LibraryManagementSystem.repo;

import com.example.LibraryManagementSystem.entity.Role;
import com.example.LibraryManagementSystem.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(RoleName roleName);
}
