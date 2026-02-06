package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientCreate(
        @NotNull(message = "Firstname is body required, cannot be null")
        @NotBlank(message = "First name cannot be blank")
        @Size(min = 3, max =30, message="First name length between 3 and 30 characters")
        String firstName,
        @NotNull(message = "Lastname is body required")
        @NotBlank(message = "Last name cannot be empty")
        @Size(min = 3, max =30, message="Last name length between 3 and 30 characters")
        String lastName
) {

}
