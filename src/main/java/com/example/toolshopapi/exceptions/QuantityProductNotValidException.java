package com.example.toolshopapi.exceptions;

public class QuantityProductNotValidException extends RuntimeException{
    public QuantityProductNotValidException(String message) {
        super(message);
    }
    public static QuantityProductNotValidException inputIsBiggerQuantity() {
        String message = "Input quantity value :  is bigger then current. or current equals zero ";
        return new QuantityProductNotValidException(message);
    }
}
