package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContactCreate(
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String name,
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "phone must contain only digits and can start with +")
        String phone,
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        @Email(message = "invalid email format")
        @Size(max = 150, message = "email cannot exceed 150 characters")
        String email,
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        @Size(max = 255, message = "address cannot exceed 255 characters")
        String address) {
}