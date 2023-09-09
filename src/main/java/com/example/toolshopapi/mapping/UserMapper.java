package com.example.toolshopapi.mapping;

import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.dto.auth.SignUpDto;
import com.example.toolshopapi.model.models.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @InheritInverseConfiguration
    User toEntity(UserDto userDto);

    default User signUpToUser(SignUpDto signUpDto) {
        return User.builder()
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .build();
    }
}
