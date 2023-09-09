package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.dto.auth.JwtResponse;
import com.example.toolshopapi.dto.auth.SignUpDto;
import com.example.toolshopapi.exceptions.DuplicateKeyException;
import com.example.toolshopapi.mapping.UserMapper;
import com.example.toolshopapi.model.models.User;
import com.example.toolshopapi.repository.RoleRepository;
import com.example.toolshopapi.security.JwtTokenProvider;
import com.example.toolshopapi.security.UserDetailsServiceImpl;
import com.example.toolshopapi.service.iterfaces.AuthService;
import com.example.toolshopapi.service.iterfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager manager;
    private final UserDetailsServiceImpl userDetails;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto register(SignUpDto signUpDto) {
        if (signUpDto == null){
            throw new IllegalArgumentException ("signUpDto is null check signUpDto value");
        }
        if (Boolean.TRUE.equals(userService.existsByEmail(signUpDto.getEmail()))) {
            throw new DuplicateKeyException("User with email " + signUpDto.getEmail() +  " is exists");
        }
        User newUser = userMapper.signUpToUser(signUpDto);
        newUser.setRoles(Set.of(roleRepository.findAllByName("ROLE_USER").get()));
        newUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        return userService.save(userMapper.toUserDto(newUser));
    }

    @Override
    @Transactional
    public JwtResponse login(SignUpDto signUpDto) {
        if (signUpDto == null){
            throw new IllegalArgumentException ("signUpDto is null check signUpDto value");
        }
        manager.authenticate(new UsernamePasswordAuthenticationToken(signUpDto.getEmail(), signUpDto.getPassword()));
        UserDetails loadUserByUsername = userDetails.loadUserByUsername(signUpDto.getEmail());
        String token = tokenProvider.generateToken(loadUserByUsername);
        return new JwtResponse(token);
    }
}
