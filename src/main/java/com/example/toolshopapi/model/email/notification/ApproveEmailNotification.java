package com.example.toolshopapi.model.email.notification;


import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class ApproveEmailNotification extends EmailNotification {

    public ApproveEmailNotification() {
        this.emailSubject = EmailSubjects.APPROVING;
    }

    @Override
    public void formatMessageBody() {
    }
}
