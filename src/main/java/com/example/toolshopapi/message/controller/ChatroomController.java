package com.example.toolshopapi.message.controller;

import com.example.toolshopapi.message.model.ChatRoomModel;
import com.example.toolshopapi.message.model.ChatRoomWithAdminRequest;
import com.example.toolshopapi.message.service.ChatRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chats")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ChatroomController {
    private final ChatRoomService chatRoomService;

    @PostMapping("/create")
    @Operation(summary = "Create chat between user and admin",
            description = "This API is used to create a chat between users.")
    public ResponseEntity<ChatRoomModel> createChat(@RequestBody ChatRoomWithAdminRequest model){
        return new ResponseEntity<>(chatRoomService.createChatRoomModel(model), HttpStatus.CREATED);
    }

}
