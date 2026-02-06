package com.example.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TrainerUpdate(@NotBlank(message = "Field name is not blank") String name,
                            @Size(min = 2000, max = 4500, message = "Trainers salary is between 2000 s/. & 4500 s/.") Double salary,
                            Integer trainee) {
}
