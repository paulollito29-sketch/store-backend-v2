package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FacultyCreate(
        @NotNull(message = "this cannot be null")
        @NotBlank(message = "this cannot be Blank")
        @Size(min = 2, max = 60, message = "the faculty name must be between 2 and 60 characters")
        String name) {
}
