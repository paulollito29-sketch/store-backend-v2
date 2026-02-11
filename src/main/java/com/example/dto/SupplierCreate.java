package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SupplierCreate(@NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             String name,
                             @NotBlank(message = "this cannot be blank")
                             @NotNull(message = "this cannot be null")
                             @Email(message = "must be a valid email")
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