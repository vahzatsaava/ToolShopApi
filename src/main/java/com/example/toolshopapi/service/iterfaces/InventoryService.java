package com.example.toolshopapi.service.iterfaces;

import com.example.toolshopapi.model.models.product.Inventory;

public interface InventoryService {
    void save(Inventory inventory);

    void updateInventoryQuantity(Long id, Integer updatedValue);
}
