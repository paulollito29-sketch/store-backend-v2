package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseCreate(String name,
                           @NotNull(message = "this cannot be null")
                           @Min(value = 1, message = "the minimum credits is 1")
                           @Max(value = 8, message = "the maximum credits is 8")
                           Integer credits,
                           @NotNull(message = "this cannot be null")
                           @Min(value = 2, message = "the minimum of weekly hour is 2")
                           @Max(value = 12, message = "the maximum of weekly hours is 12")
                           Integer hours,
                           @NotNull(message = "this cannot be null")
                           @NotBlank(message = "this cannot be blank")
                           String modality) {
}
