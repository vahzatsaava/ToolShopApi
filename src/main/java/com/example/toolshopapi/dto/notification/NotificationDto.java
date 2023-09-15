package com.example.toolshopapi.dto.notification;

import com.example.toolshopapi.model.email.constants.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private Long id;
    private String firstName;
    private String email;
    private NotificationType notificationType;
}
