package com.example.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SupplierUpdate(@NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String name,
                             @NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String email,
                             @NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String phone,
                             @NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String address) {
}