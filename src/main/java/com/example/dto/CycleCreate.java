package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CycleCreate(
        @NotNull(message = "this cannot eb null")
        @NotBlank(message = "this cannot be blank")
        String name) {
}
