package com.example.dto;

import java.util.List;

public record FacultyFindOne(Long facultyId, String name, List<SpecialityFindAll> specialities) {
}
