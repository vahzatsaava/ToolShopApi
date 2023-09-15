package com.example.toolshopapi.model.email.notification;


import com.example.toolshopapi.model.email.constants.EmailMessages;
import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class PasswordEmailNotification extends EmailNotification {

    public PasswordEmailNotification() {
        this.emailSubject = EmailSubjects.PASSWORD;
    }

    @Override
    public void formatMessageBody() {
        this.body = String.format(EmailMessages.PASSWORD, firstName);
    }
}
