package com.example.toolshopapi.controller;

import com.example.toolshopapi.dto.AddressDto;
import com.example.toolshopapi.dto.UserAdditionalDto;
import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.dto.general.ResponseDto;
import com.example.toolshopapi.service.iterfaces.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @PutMapping("/update-user-info")
    public ResponseEntity<ResponseDto<UserDto>> updateUserAddressAndName(Principal principal,
                                                           @RequestBody @Valid UserAdditionalDto userAdditionalDto,
                                                           @RequestBody @Valid AddressDto addressDto){

        UserDto updated = userService.updateUserAddressAndName(principal, userAdditionalDto,addressDto);
        ResponseDto<UserDto> responseDto = new ResponseDto<>(HttpStatus.OK.value(), updated);

        return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping("/delete-own-account")
    public ResponseEntity<ResponseDto<String>> deleteAccount(Principal principal){
        userService.deleteAccount(principal);
        ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.OK.value(),
                String.format("user with email %s was deleted ",principal.getName()));
        return ResponseEntity.ok(responseDto);
    }
}