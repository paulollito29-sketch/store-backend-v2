package com.example.dto;

import jakarta.validation.constraints.*;

public record StudentCreate(@NotBlank(message = "this cannot be blank")
                            @NotNull(message = "this cannot be null")
                            @Size(max = 1000)
                            String firstName,
                            @NotBlank(message = "this cannot be blank")
                            @NotNull(message = "this cannot be null")
                            String lastName,
                            @NotNull(message = "this cannot be null")
                            @Min(value = 5, message = "the min value is 5")
                            @Max(value = 19, message = "the max value is 19")
                            Integer age ,
                            @NotBlank(message = "this cannot be blank")
                            @NotNull(message = "this cannot be null")
                            String grade){
}
