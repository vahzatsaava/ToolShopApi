package com.example.toolshopapi.message.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chat_room_entity")
public class ChatRoomEntity {

    public ChatRoomEntity(Long id) {
        this.id = id;
    }

    public ChatRoomEntity(String name) {
        this.name = name;
    }

    public boolean addMessageEntity(MessageEntity message){
        return messages.add(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    private Long picture;
    // Связь с сообщениями в этой комнате чата
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageEntity> messages = new ArrayList<>();

    // Связь с пользователями, участвующими в этом чате
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserChatRoomEntity> users = new ArrayList<>();


}

