package com.example.toolshopapi.message.service;

import com.example.toolshopapi.message.mapper.ChatRoomMapper;
import com.example.toolshopapi.message.entity.ChatRoomEntity;
import com.example.toolshopapi.message.entity.UserChatRoomEntity;
import com.example.toolshopapi.message.model.ChatRoomModel;
import com.example.toolshopapi.message.model.ChatRoomWithAdminRequest;
import com.example.toolshopapi.message.model.MessageModel;
import com.example.toolshopapi.message.repo.ChatRoomRepository;
import com.example.toolshopapi.model.models.User;
import com.example.toolshopapi.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserServiceImpl userService;
    private final UserChatroomService userChatroomService;
    private final ChatRoomMapper chatRoomMapper;

    @Transactional
    public ChatRoomModel createChatRoomModel(ChatRoomWithAdminRequest request) {
        ChatRoomEntity chatRoom = chatRoomMapper.toChatRoomEntity(request);
        ChatRoomEntity chatCreated = chatRoomRepository.save(chatRoom);

        User userCurrent = userService.getCurrentUser();
        User userById = userService.findById(request.getUserId());

        UserChatRoomEntity chatRoomCurrentUser = userChatroomService.addUserToChatRoom(userCurrent, chatCreated);
        UserChatRoomEntity chatRoomUserById = userChatroomService.addUserToChatRoom(userById, chatCreated);

        chatCreated.setUsers(List.of(chatRoomCurrentUser, chatRoomUserById));


        return chatRoomMapper.toChatRoomModel(chatCreated);

    }

    @Transactional
    public void deleteChatRoom(Long chatRoomId) {
        Optional<ChatRoomEntity> chatRoom = chatRoomRepository.findById(chatRoomId);
        chatRoom.ifPresent(chatRoomRepository::delete);
    }
    public ChatRoomEntity findById(Long id){
        return chatRoomRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Chatroom not found by id " + id));
    }

    public ChatRoomModel get(Long id) {
        ChatRoomModel chatRoomModel = chatRoomMapper.toChatRoomModel(chatRoomRepository.findById(id).orElseThrow());

        // Обрезаем историю
        List<MessageModel> lastMessages = chatRoomModel.getLastMessages();
        chatRoomModel.setLastMessages(subList(lastMessages, 15));

        return chatRoomModel;
    }

    public List<ChatRoomModel> getByUser() {
        User user = userService.getCurrentUser();

        List<UserChatRoomEntity> userChatRoomEntities = userChatroomService.getByUserId(user);

        return userChatRoomEntities.stream().map(
                        x -> {
                            ChatRoomModel chatRoomModel = chatRoomMapper.toChatRoomModel(x.getChatRoom());
                            List<MessageModel> lastMessages = chatRoomModel.getLastMessages();
                            chatRoomModel.setLastMessages(subList(lastMessages, 2));

                            return chatRoomModel;
                        }
                )
                .collect(Collectors.toList());
    }

    private List<MessageModel> subList(List<MessageModel> lastMessages, int n) {
        if (lastMessages.size() > n) {
            return lastMessages.subList(lastMessages.size() - n, lastMessages.size());
        }
        return lastMessages;
    }

}
