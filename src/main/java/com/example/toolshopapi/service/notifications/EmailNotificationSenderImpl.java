package com.example.toolshopapi.service.notifications;

import com.example.toolshopapi.model.email.notification.EmailNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class EmailNotificationSenderImpl implements EmailNotificationSender {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    @Async
    public void sendLetter(EmailNotification emailNotification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailNotification.getEmailTo());
        message.setSubject(emailNotification.getEmailSubject());
        message.setText(emailNotification.getBody());

        javaMailSender.send(message);
    }


}
