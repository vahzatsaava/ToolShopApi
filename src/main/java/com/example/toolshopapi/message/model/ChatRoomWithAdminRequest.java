package com.example.toolshopapi.message.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatRoomWithAdminRequest {
    @NotNull
    private Long userId;
    @NotNull
    private String name;
    @NotNull
    private String color;
    @NotNull
    private Long picture;
}
