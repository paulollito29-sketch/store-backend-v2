package com.example.dto;

public record StudentUpdated(Long studentId,
                             String firstName,
                             String lastName,
                             Integer age ,
                             String grade) {
}
