package com.example.dto;

import jakarta.validation.constraints.NotNull;

public record ClientFindAll(Long idClient, @NotNull String firstName, @NotNull String lastName) {
}
