package com.example.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TeacherSectionUpdated(Long teacherId, Long sectionId) {
}
