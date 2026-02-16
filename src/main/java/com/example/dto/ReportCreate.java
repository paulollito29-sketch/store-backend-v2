package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReportCreate(
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String type,
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String name,
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String description) {
}
