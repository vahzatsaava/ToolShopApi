package com.example.toolshopapi.message.entity;


import com.example.toolshopapi.model.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "message_entity")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoomEntity chatRoom;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private ZonedDateTime timestamp;

}
