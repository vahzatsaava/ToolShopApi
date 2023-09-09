package com.example.toolshopapi.service.iterfaces;

import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.dto.auth.JwtResponse;
import com.example.toolshopapi.dto.auth.SignUpDto;

public interface AuthService {
    UserDto register(SignUpDto signUpDto);

    JwtResponse login(SignUpDto signUpDto);
}
