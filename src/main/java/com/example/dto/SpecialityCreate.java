package com.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SpecialityCreate(
        @NotNull(message = "this cannot be null")
        @Min(value = 1, message = "the minimum is 1")
        Long facultyId,
        @NotNull(message = "this cannot be null")
        @NotBlank(message = "this cannot be blank")
        @Size(min = 1, max = 30, message = "the faculty name mast be between 1 and 35 characters")
        String name) {
}
