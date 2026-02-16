package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomCreate(
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String course,
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String block) {
}
