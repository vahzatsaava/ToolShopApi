package com.example.toolshopapi.message.service;

import com.example.toolshopapi.message.entity.ChatRoomEntity;
import com.example.toolshopapi.message.entity.UserChatRoomEntity;
import com.example.toolshopapi.message.repo.UserChatRoomRepository;
import com.example.toolshopapi.model.models.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserChatroomService {
    private final UserChatRoomRepository userChatRoomRepository;

    @Transactional
    public UserChatRoomEntity addUserToChatRoom(User user, ChatRoomEntity chatRoom) {
        UserChatRoomEntity userChatRoom = new UserChatRoomEntity();
        userChatRoom.setUser(user);
        userChatRoom.setChatRoom(chatRoom);
        return userChatRoomRepository.save(userChatRoom);
    }
    public List<UserChatRoomEntity> getByUserId(User user) {
        return userChatRoomRepository.findByUser(user);
    }
}
