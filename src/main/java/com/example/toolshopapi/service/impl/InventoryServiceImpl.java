package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.model.models.product.Inventory;
import com.example.toolshopapi.repository.InventoryRepository;
import com.example.toolshopapi.service.iterfaces.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional
    public void save(Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("inventoryDto is null");
        }

        inventoryRepository.save(inventory);

    }
}
