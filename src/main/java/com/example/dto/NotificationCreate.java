package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationCreate(
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String body,
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String title
) {
}
