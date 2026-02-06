package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TrainerCreate(
        @NotBlank(message = "Field name is not blank")
        String name,
        @Min(value = 1130, message = "the minimum is 1130")
        Double salary,
        @Min(value = 1, message = "the minimum is 1")
        @Max(value = 5, message = "maximum 5 trainees ")
        Integer trainee) {
}
