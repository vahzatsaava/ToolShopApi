package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.product_dto.InventoryDto;
import com.example.toolshopapi.mapping.InventoryMapper;
import com.example.toolshopapi.model.models.product.Inventory;
import com.example.toolshopapi.repository.InventoryRepository;
import com.example.toolshopapi.service.iterfaces.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    @Transactional
    public void save(InventoryDto inventoryDto) {
        if (inventoryDto == null) {
            throw new IllegalArgumentException("inventoryDto is null");
        }
        Inventory saved = inventoryMapper.toEntity(inventoryDto);
        inventoryRepository.save(saved);

    }
}
