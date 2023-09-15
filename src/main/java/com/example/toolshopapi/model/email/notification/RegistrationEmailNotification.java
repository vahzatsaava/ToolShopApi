package com.example.toolshopapi.model.email.notification;


import com.example.toolshopapi.model.email.constants.EmailMessages;
import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class RegistrationEmailNotification extends EmailNotification {

    public RegistrationEmailNotification() {
        this.emailSubject = EmailSubjects.REGISTRATION;
    }

    @Override
    public void formatMessageBody() {
        this.body = String.format(EmailMessages.SUCCESSFUL_REGISTRATION, firstName, "[someUrl]");
    }
}
