package com.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnimalUpdate(@NotNull(message = "Race is body required, cannot be null")
                           @NotBlank(message = "Race name cannot be blank")
                           String raza,
                           @NotNull(message = "Race is body required, cannot be null")
                           @NotBlank(message = "Race name cannot be blank")
                           String name,
                           @NotNull(message = "Age is Body required")
                           @Min(value = 1)
                           Integer age,
                           @Min(value = 0)
                           @NotNull(message = "weight is Body required")
                           Double weight) {
}
