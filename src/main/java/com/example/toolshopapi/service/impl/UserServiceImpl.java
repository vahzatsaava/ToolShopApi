package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.mapping.UserMapper;
import com.example.toolshopapi.model.models.User;
import com.example.toolshopapi.repository.UserRepository;
import com.example.toolshopapi.service.iterfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDto findUserByEmail(String email) {
        if (email == null){
            throw new IllegalArgumentException("email is null, we cannot find user by email");
        }
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(" entity with email " + email + " not found"));
        return userMapper.toUserDto(user);
    }
}
