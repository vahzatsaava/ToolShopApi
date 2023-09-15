package com.example.toolshopapi.model.email.notification;

import com.example.toolshopapi.model.email.constants.EmailMessages;
import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class ReactivateAccountNotification extends EmailNotification {

    public ReactivateAccountNotification() {
        this.emailSubject = EmailSubjects.REACTIVATED;
    }

    @Override
    public void formatMessageBody() {
        this.body = String.format(EmailMessages.REACTIVATED_MESSAGE, firstName);
    }
}
