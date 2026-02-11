package com.example.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventCreate(@NotBlank(message = "this cannot be blank")
                          @NotNull(message = "this cannot be null")
                          String name,
                          @NotBlank(message = "this cannot be blank")
                          @NotNull(message = "this cannot be null")
                          String description,
                          @NotBlank(message = "this cannot be blank")
                          @NotNull(message = "this cannot be null")
                          String location,
                          @FutureOrPresent(message = "event date cannot be before current date")
                          @NotNull(message = "this cannot be null")
                          LocalDate eventDate) {
}