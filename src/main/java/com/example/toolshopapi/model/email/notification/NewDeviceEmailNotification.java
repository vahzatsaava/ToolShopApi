package com.example.toolshopapi.model.email.notification;


import com.example.toolshopapi.model.email.constants.EmailMessages;
import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class NewDeviceEmailNotification extends EmailNotification {

    public NewDeviceEmailNotification() {
        this.emailSubject = EmailSubjects.NEW_DEVICE;
    }

    @Override
    public void formatMessageBody() {
        this.body = String.format(EmailMessages.NEW_DEVICE, firstName);
    }
}
