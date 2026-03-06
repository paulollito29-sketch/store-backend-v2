package com.example.dto;

import jakarta.validation.constraints.NotNull;


public record TeacherSectionCreate(
        @NotNull(message = "this cannot be null")
        Long teacherId,
        @NotNull(message = "this cannot be null")
        Long sectionId) {

}
