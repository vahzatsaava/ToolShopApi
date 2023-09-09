package com.example.toolshopapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Street cannot be empty")
    private String street;

    @NotBlank(message = "Building number cannot be empty")
    private String buildingNumber;

    @NotBlank(message = "Apartment number cannot be empty")
    private String apartmentNumber;

    @NotBlank(message = "Postal code cannot be empty")
    @Size(min = 5, max = 10, message = "Postal code must be between 5 and 10 characters")
    private String postCode;
}
