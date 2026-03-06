package com.example.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TeacherSectionUpdate(
        @NotNull(message = "this cannot be null")
        Long teacherId,
        @NotNull(message = "this cannot be null")
        Long sectionsId) {
}
