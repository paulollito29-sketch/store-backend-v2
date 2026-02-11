package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieUpdate(String name,
        @NotBlank(message = "director cannot be blank")
                          @NotNull(message = "director cannot be null")
                          String director,
                          Integer duration,
                          @Max(value = 2026, message = "wtf bro its 2026")
                          Integer year,
                          @NotBlank(message = "genre cannot be blank")
                          @NotNull(message = "genre cannot be null")
                          String genre) {
}
