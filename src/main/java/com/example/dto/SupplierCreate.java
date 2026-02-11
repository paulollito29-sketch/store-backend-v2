package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SupplierCreate(@NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String name,
                             @NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             @Email(message = "must be a valid email")
                             String email,
                             @NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String phone,
                             @NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String address) {
}