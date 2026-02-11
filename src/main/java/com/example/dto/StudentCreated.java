package com.example.dto;

public record StudentCreated(Long studentId,
                             String firstName,
                             String lastName,
                             Integer age ,
                             String grade) {
}
