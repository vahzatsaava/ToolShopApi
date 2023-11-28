package com.example.toolshopapi.message.controller.ws;

import com.example.toolshopapi.message.model.MessageModel;
import com.example.toolshopapi.message.model.MessageRequest;
import com.example.toolshopapi.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;


import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MessageService messageService;

    @MessageMapping("/chat/{chatId}/sendUserMessage")
    public MessageModel getMessage(@DestinationVariable Long chatId, @Payload MessageRequest message, Principal principal) {
        return messageService.sendMessageToUser(chatId, message, principal);
    }
}
