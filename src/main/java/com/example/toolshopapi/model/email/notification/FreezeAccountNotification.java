package com.example.toolshopapi.model.email.notification;


import com.example.toolshopapi.model.email.constants.EmailMessages;
import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class FreezeAccountNotification extends EmailNotification {

    public FreezeAccountNotification() {
        this.emailSubject = EmailSubjects.FREEZE;
    }

    @Override
    public void formatMessageBody() {
        this.body = String.format(EmailMessages.FREEZE_MESSAGE, firstName);
    }
}
