package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FoodUpdate(
        @NotNull(message = "type cannot be null")
        @NotBlank(message = "type cannot be blank")
        String type,
        @NotNull(message = "price cannot be null")
        Double price,
        @NotNull(message = "name cannot be null")
        @NotBlank(message = "name cannot be blank")
        String name,
        @NotNull(message = "ingredients cannot be null")
        @NotBlank(message = "ingredients cannot be blank")
        String ingredients) {
}
