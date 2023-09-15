package com.example.toolshopapi.model.email.constants;

import com.example.toolshopapi.model.email.notification.*;

import java.util.function.Supplier;

public enum NotificationType {

    ADDRESS(AddressEmailNotification::new),
    APPROVE_EMAIL(ApproveEmailNotification::new),
    APPROVE_USER(ApproveUserNotification::new),
    DELETE(DeleteAccountNotification::new),
    FREEZE(FreezeAccountNotification::new),
    NEW_DEVICE(NewDeviceEmailNotification::new),
    PASSWORD(PasswordEmailNotification::new),
    QUESTION(QuestionEmailNotification::new),
    REACTIVATE(ReactivateAccountNotification::new),
    REGISTRATION(RegistrationEmailNotification::new),
    REJECTED(RejectedAccountNotification::new);

    private final Supplier<EmailNotification> notificationSupplier;

    NotificationType(Supplier<EmailNotification> notificationSupplier) {
        this.notificationSupplier = notificationSupplier;
    }

    public EmailNotification createNotification() {
        return notificationSupplier.get();
    }
}