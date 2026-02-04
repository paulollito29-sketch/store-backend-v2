package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.aspectj.bridge.IMessage;

public record BookCreate(String title, String description, Double price,
                         @Min(value = 1, message = "Field stock is minimun 1")
                         @Max(value = 20, message = "field is maximum 20")
                         Integer stock) {
}
