package com.example.toolshopapi.mapping;

import com.example.toolshopapi.dto.AddressDto;
import com.example.toolshopapi.model.models.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address address);

    @InheritInverseConfiguration
    Address toEntity(AddressDto addressDto);
}
