package com.example.toolshopapi.service.iterfaces;

import com.example.toolshopapi.dto.AddressDto;
import com.example.toolshopapi.dto.UserAdditionalDto;
import com.example.toolshopapi.dto.UserDto;

import java.security.Principal;

public interface UserService {
    UserDto findUserByEmail(String email);
    UserDto save(UserDto user);
    Boolean existsByEmail(String email);
    UserDto updateUserAddressAndName(Principal principal, UserAdditionalDto userDto, AddressDto addressDto);
    void deleteAccount(Principal principal);
    void deleteUserByAdmin(Long id);
}
