package com.example.toolshopapi.dto;

import com.example.toolshopapi.model.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String email;

    private String password;

    private UserInfoDto userInfo;

    private Set<Role> roles;
}
