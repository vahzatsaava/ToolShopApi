package com.example.toolshopapi.message.service;

import com.example.toolshopapi.message.entity.ChatRoomEntity;
import com.example.toolshopapi.message.entity.MessageEntity;
import com.example.toolshopapi.message.mapper.MessageMapper;
import com.example.toolshopapi.message.model.MessageModel;
import com.example.toolshopapi.message.model.MessageRequest;
import com.example.toolshopapi.message.repo.MessageRepository;
import com.example.toolshopapi.model.models.User;
import com.example.toolshopapi.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {
    private final SimpMessagingTemplate messagingTemplate;
    private final UserServiceImpl userService;
    private final ChatRoomService chatRoomService;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;


    public MessageModel sendMessageToUser(Long chatId, MessageRequest request, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        ChatRoomEntity chatRoom = chatRoomService.findById(chatId);

        MessageEntity message = generateMessageEntity(request, chatRoom, user);

        messagingTemplate.convertAndSendToUser(principal.getName(),"/topic/chat/"+ chatId,message);

        return messageMapper.toMessageModel(message);

    }

    private MessageEntity generateMessageEntity(MessageRequest messageRequest, ChatRoomEntity chatRoom, User user) {
        MessageEntity message = new MessageEntity();
        message.setUser(user);
        message.setChatRoom(chatRoom);
        message.setContent(messageRequest.getContent());
        message.setTimestamp(ZonedDateTime.now());

        messageRepository.saveAndFlush(message);
        return message;
    }
}
