package com.example.toolshopapi.model.email.notification;


import com.example.toolshopapi.model.email.constants.EmailMessages;
import com.example.toolshopapi.model.email.constants.EmailSubjects;

public class QuestionEmailNotification extends EmailNotification {

    public QuestionEmailNotification() {
        this.emailSubject = EmailSubjects.CHANGING_QUESTION;
    }

    @Override
    public void formatMessageBody() {
        this.body = String.format(EmailMessages.CHANGING_QUESTION, firstName);
    }
}
