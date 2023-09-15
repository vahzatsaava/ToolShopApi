package com.example.toolshopapi.model.email.notification;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class EmailNotification {

    protected String emailSubject;
    protected String body;
    protected String firstName;
    protected String emailTo;

    public abstract void formatMessageBody();

}
