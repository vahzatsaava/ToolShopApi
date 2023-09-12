package com.example.toolshopapi.controller;

import com.example.toolshopapi.dto.UserAdditionalDto;
import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.dto.general.ResponseDto;
import com.example.toolshopapi.service.iterfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @PostMapping("/update-user-info")
    @Operation(summary = "Update user address and name",
            description = "This API is used to update the address and name of the authenticated user.")
    public ResponseEntity<ResponseDto<UserDto>> updateUserAddressAndName(@Valid @RequestBody UserAdditionalDto userAdditionalDto,
                                                                         Principal principal
                                                           ){

        UserDto updated = userService.updateUserAddressAndName(principal, userAdditionalDto);
        ResponseDto<UserDto> responseDto = new ResponseDto<>(HttpStatus.OK.value(), updated);

        return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping("/delete-own-account")
    @Operation(summary = "Delete own account",
            description = "This API is used to allow authenticated users to delete their own account.")
    public ResponseEntity<ResponseDto<String>> deleteAccount(Principal principal){
        userService.deleteAccount(principal);
        ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.OK.value(),
                String.format("user with email %s was deleted ",principal.getName()));
        return ResponseEntity.ok(responseDto);
    }
}
