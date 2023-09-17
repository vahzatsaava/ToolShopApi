package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.model.models.product.Inventory;
import com.example.toolshopapi.repository.InventoryRepository;
import com.example.toolshopapi.service.iterfaces.InventoryService;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    @Transactional
    public void updateInventoryQuantity(Long id, Integer updatedValue) {
        if (updatedValue == null || id == null) {
            throw new IllegalArgumentException("inventoryDto or id is null");
        }
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("inventory quantity not found by id " + id));
        inventory.setAvailableQuantity(updatedValue);

        inventoryRepository.save(inventory);
    }
}
