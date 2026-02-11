package com.example.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SupplierUpdate(@NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String name,
                             @NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String email,
                             @Pattern(
                                     regexp = "^\\d{3}\\s\\d{3}\\s\\d{3}$",
                                     message = "Invalid format. Use: 123 456 789"
                             )
                             @NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String phone,
                             @NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String address) {
}