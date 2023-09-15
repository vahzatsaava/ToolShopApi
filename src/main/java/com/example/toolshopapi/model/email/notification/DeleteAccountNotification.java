package com.example.toolshopapi.model.email.notification;


import com.example.toolshopapi.model.email.constants.EmailMessages;
import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class DeleteAccountNotification extends EmailNotification {

    public DeleteAccountNotification() {
        this.emailSubject = EmailSubjects.DELETED;
    }

    @Override
    public void formatMessageBody() {
        this.body = String.format(EmailMessages.DELETED_MESSAGE, firstName);
    }
}
