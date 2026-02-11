package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TeacherUpdate(@NotBlank(message = "this cannot be blank")
                            @NotNull(message = "this cannot be null")
                            String firstName,
                            @NotBlank(message = "this cannot be blank")
                            @NotNull(message = "this cannot be null")
                            String lastName,
                            @NotBlank(message = "this cannot be blank")
                            @NotNull(message = "this cannot be null")
                            String email,
                            @NotBlank(message = "this cannot be blank")
                            @NotNull(message = "this cannot be null")
                            String subject) {
}