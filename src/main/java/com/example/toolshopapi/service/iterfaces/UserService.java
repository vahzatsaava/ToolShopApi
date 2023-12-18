package com.example.toolshopapi.service.iterfaces;

import com.example.toolshopapi.dto.UserAdditionalDto;
import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.model.models.User;

import java.security.Principal;

public interface UserService {
    UserDto findUserByEmail(String email);
    UserDto save(UserDto user);
    Boolean existsByEmail(String email);
    UserDto updateUserAddressAndName(Principal principal, UserAdditionalDto userDto);
    void deleteAccount(Principal principal);
    User findById(Long id);
    User getCurrentUser();
    void deleteUserByAdmin(Long id);
}
