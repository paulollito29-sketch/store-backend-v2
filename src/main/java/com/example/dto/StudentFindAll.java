package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentFindAll(String firstName,
                             String lastName,
                             Integer age ,
                             String grade) {
}
