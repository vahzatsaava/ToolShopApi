package com.example.toolshopapi.mapping;

import com.example.toolshopapi.dto.product_dto.InventoryDto;
import com.example.toolshopapi.model.models.product.Inventory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryDto toDto(Inventory inventory);

    @InheritInverseConfiguration
    Inventory toEntity(InventoryDto inventoryDto);
}
