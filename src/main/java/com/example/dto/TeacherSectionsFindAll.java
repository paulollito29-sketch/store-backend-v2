package com.example.dto;

import java.util.List;

public record TeacherSectionsFindAll (Long idTeacher, List<SectionFindAll> sections){
}
