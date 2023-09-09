package com.example.toolshopapi.service.iterfaces;

import com.example.toolshopapi.dto.UserDto;

public interface UserService {
    UserDto findUserByEmail(String email);
    UserDto save(UserDto user);
    Boolean existsByEmail(String email);
}
