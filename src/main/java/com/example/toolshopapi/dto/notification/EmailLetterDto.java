package com.example.toolshopapi.dto.notification;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class EmailLetterDto {
    private String emailSubject;
    private Timestamp sent;
    private Boolean isRead;
}
