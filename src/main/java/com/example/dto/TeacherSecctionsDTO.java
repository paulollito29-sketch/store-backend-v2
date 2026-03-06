package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeacherSecctionsDTO {

    private Long teacherId;
    private String nameComplete;
    private List<String> sections;
}
