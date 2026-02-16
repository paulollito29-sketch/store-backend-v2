package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CountryCreate(
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        @Size(min = 2, max = 255,message = " country name must not be more than 255 characters")
        String name,
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        @Size(max = 3, min = 3, message = "The code must have 3 numbers")
        @Pattern(regexp = "\\d{3}", message = "The code must contain exactly 3 positive digits")
        String code) {
}