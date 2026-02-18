package com.example.dto;

import java.util.List;

public record FacultyFindAll(Long facultyId, String name, List<SpecialityFindAll> specialities) {
}
