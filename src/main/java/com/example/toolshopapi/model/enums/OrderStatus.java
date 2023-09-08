package com.example.toolshopapi.model.enums;

public enum OrderStatus {
    PENDING, // Заказ создан, но не обработан
    PROCESSING, // Заказ обрабатывается
    SHIPPED, // Заказ отправлен клиенту
    DELIVERED, // Заказ доставлен клиенту
    CANCELED // Заказ отменен

}
