package com.example.toolshopapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private Long id;

    private String firstName;

    private String lastName;

    private boolean emailConfirmed;

    private String emailConfirmationToken;

    private AddressDto shippingAddress;
}
