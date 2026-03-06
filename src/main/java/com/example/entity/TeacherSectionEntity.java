package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teacher_section")
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(TeacherSectionPK.class)
@Builder

public class TeacherSectionEntity {

    @Id
    private TeacherEntity teacher;

    @Id
    private SectionEntity section;


}
