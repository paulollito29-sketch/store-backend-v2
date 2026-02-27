package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SectionUpdate(
        @NotBlank(message = "Field name is not blank")
        @Size(min = 3, max = 50, message = "Field name is max lenght between 3 and 50")
        String name) {
}
