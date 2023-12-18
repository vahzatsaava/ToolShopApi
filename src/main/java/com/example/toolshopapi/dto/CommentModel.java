package com.example.toolshopapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentModel {

    private Long id;

    private String text;

    private LocalDateTime created;
}
