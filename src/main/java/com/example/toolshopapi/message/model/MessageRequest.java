package com.example.toolshopapi.message.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MessageRequest {
    @NotBlank
    private String content;
}

