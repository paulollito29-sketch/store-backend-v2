package com.example.dto;

import jakarta.validation.constraints.NotNull;

public record ClientUpdate(@NotNull String firstName, @NotNull String lastName) {
}
