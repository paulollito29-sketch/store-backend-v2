package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentUpdate(@NotBlank(message = "this cannot be blank")
                            @NotNull(message = "this cannot be null")
                            String firstName,
                            @NotBlank(message = "this cannot be blank")
                            @NotNull(message = "this cannot be null")
                            String lastName,
                            @NotNull(message = "this cannot be null")
                            @Min(value = 5, message = "the min value is 5")
                            @Max(value = 19, message = "the max value is 19")
                            Integer age ,
                            @NotBlank(message = "this cannot be blank")
                            @NotNull(message = "this cannot be null")
                            String grade) {
}
