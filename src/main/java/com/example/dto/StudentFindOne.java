package com.example.dto;

public record StudentFindOne(Long studentId,
                             String firstName,
                             String lastName,
                             Integer age ,
                             String grade) {
}
