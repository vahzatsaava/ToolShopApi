package com.example.toolshopapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String city;

    private String street;

    private String buildingNumber;

    private String apartmentNumber;

    private String postCode;
}
