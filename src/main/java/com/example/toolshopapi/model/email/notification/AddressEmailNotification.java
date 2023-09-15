package com.example.toolshopapi.model.email.notification;


import com.example.toolshopapi.model.email.constants.EmailMessages;
import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class AddressEmailNotification extends EmailNotification {

    public AddressEmailNotification() {
        this.emailSubject = EmailSubjects.ADDRESS;
    }

    @Override
    public void formatMessageBody() {
        this.body = String.format(EmailMessages.CHANGE_ADDRESS, firstName);
    }
}
