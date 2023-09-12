package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.UserAdditionalDto;
import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.mapping.AddressMapper;
import com.example.toolshopapi.mapping.UserMapper;
import com.example.toolshopapi.model.models.User;
import com.example.toolshopapi.repository.UserRepository;
import com.example.toolshopapi.service.iterfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;


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

    @Override
    @Transactional
    public UserDto save(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("entity user is null ");
        }
        User userEntity = userRepository.save(userMapper.toEntity(user));

        return userMapper.toUserDto(userEntity);
    }

    @Override
    @Transactional
    public UserDto updateUserAddressAndName(Principal principal, UserAdditionalDto userAdditionalDto) {
        if (userAdditionalDto == null ) {
            throw new IllegalArgumentException("entity user is null ");
        }
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException(" entity with email " + principal.getName() + " not found"));

        userRepository.save(updateUserFields(user,userAdditionalDto));

        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByEmail(String email) {

        if (email == null) {
            throw new IllegalArgumentException("email is null, we cannot find user by email");
        }
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void deleteAccount(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException(" entity with email " + principal.getName() + " not found"));
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void deleteUserByAdmin(Long id) {
        if (id == null){
            throw new IllegalArgumentException("id is null, check value ");

        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(" entity with id " + id + " not found"));
        userRepository.delete(user);
    }

    private User updateUserFields(User user, UserAdditionalDto userAdditionalDto) {
        user.setFirstName(userAdditionalDto.getFirstName());
        user.setLastName(userAdditionalDto.getLastName());
        user.setShippingAddress(addressMapper.toEntity(userAdditionalDto.getAddressDto()));
        return user;
    }
}
