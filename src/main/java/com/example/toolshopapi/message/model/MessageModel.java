package com.example.toolshopapi.message.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.ZonedDateTime;
@Data
public class MessageModel {
    private Long userId;
    private Long chatRoomId;
    @NotBlank
    private String content;
    private String role;
    private ZonedDateTime timestamp;
}
