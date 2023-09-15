package com.example.toolshopapi.service.notifications;


import com.example.toolshopapi.model.email.notification.EmailNotification;

public interface EmailNotificationSender {

    void sendLetter(EmailNotification emailNotification);

}
