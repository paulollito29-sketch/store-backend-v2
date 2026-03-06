package com.example.dto;

import com.example.entity.SectionEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TeacherSectionCreate(
        @NotNull(message = "this cannot be null")
        Long teacherId,
        @NotNull(message = "this cannot be null")
        List<Long> sectionsId) {

}
