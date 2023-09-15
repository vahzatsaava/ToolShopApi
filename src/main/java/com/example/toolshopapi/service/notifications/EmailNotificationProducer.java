package com.example.toolshopapi.service.notifications;

import com.example.toolshopapi.dto.notification.NotificationDto;
import com.example.toolshopapi.model.email.notification.EmailNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificationProducer {


    private final EmailNotificationSender notificationSender;

    @EventListener
    public void handleCommonNotification(NotificationDto notificationDto) {

        EmailNotification notification = notificationDto.getNotificationType().createNotification();
        notification.setEmailTo(notificationDto.getEmail());
        notification.setFirstName(notificationDto.getFirstName());
        notification.formatMessageBody();

        notificationSender.sendLetter(notification);
    }
}
