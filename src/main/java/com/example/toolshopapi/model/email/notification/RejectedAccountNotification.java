package com.example.toolshopapi.model.email.notification;


import com.example.toolshopapi.model.email.constants.EmailMessages;
import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class RejectedAccountNotification extends EmailNotification {

    public RejectedAccountNotification() {
        this.emailSubject = EmailSubjects.REJECTED_ACCOUNT;
    }

    @Override
    public void formatMessageBody() {
        this.body = String.format(EmailMessages.REJECTED_ACCOUNT, firstName);
    }
}
