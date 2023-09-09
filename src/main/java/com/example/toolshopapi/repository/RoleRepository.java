package com.example.toolshopapi.repository;

import com.example.toolshopapi.model.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findAllByName(String name);

}
