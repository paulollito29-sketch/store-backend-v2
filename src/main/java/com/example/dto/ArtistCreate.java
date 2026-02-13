package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArtistCreate(
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String name,
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String country,
        @NotBlank(message = "this cannot be blank")
        @NotNull(message = "this cannot be null")
        String genre,
        @Max(value = 9999, message = "the maximum year is 9999")
        @Min(value = 1000, message = "the minimum year is 1000")
        @NotNull(message = "this can    not be null")
        Integer debutYear) {
}