package com.example.toolshopapi.message.model;

import lombok.Data;

import java.util.List;

@Data
public class ChatRoomModel {
    private Long id;
    private String name;
    private String color;
    private Long picture;
    private List<Long> users;
    private List<MessageModel> lastMessages;
}