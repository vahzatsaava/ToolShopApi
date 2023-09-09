package com.example.toolshopapi.controller;

import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.dto.auth.JwtResponse;
import com.example.toolshopapi.dto.auth.SignUpDto;
import com.example.toolshopapi.dto.general.ResponseDto;
import com.example.toolshopapi.service.iterfaces.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    @Operation(summary = "[US 1.1] Prove our users login ",
            description = "This API is used to login and authenticate.")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid SignUpDto signUpDto) {
        return ResponseEntity.ok(authService.login(signUpDto));
    }

    @PostMapping("/register")
    @Operation(summary = "[US 1.2] Register new user for api ",
            description = "This API is used for registration .")
    public ResponseEntity<ResponseDto<UserDto>> register(@Valid @RequestBody SignUpDto signUpDto) {
        UserDto userDto = authService.register(signUpDto);
        ResponseDto<UserDto> responseDto = new ResponseDto<>(HttpStatus.CREATED.value(), userDto);
        return ResponseEntity.ok(responseDto);
    }


}
