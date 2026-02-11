package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record EventUpdate (@NotBlank(message = "this cannot be blank")
                           @NotNull(message = "this cannot be null")
                           String name,
                           @NotBlank(message = "this cannot be blank")
                           @NotNull(message = "this cannot be null")
                           String description,
                           @NotBlank(message = "this cannot be blank")
                           @NotNull(message = "this cannot be null")
                           String location,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           @NotNull(message = "this cannot be null")
                           LocalDate eventDate) {
}
