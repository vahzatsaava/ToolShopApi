package com.example.toolshopapi.model.models.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receipt_time")
    private LocalDateTime receiptTime;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @Column(name = "available_quantity")
    private Integer availableQuantity;
}
