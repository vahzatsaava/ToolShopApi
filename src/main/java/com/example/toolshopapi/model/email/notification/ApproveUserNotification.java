package com.example.toolshopapi.model.email.notification;


import com.example.toolshopapi.model.email.constants.EmailMessages;
import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class ApproveUserNotification extends EmailNotification {

    public ApproveUserNotification() {
        this.emailSubject = EmailSubjects.ACTIVATED_ACCOUNT;
    }

    @Override
    public void formatMessageBody() {
        this.body = String.format(EmailMessages.APPROVED_ACCOUNT, firstName);
    }
}
