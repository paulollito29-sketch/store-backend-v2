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
@Builder

public class TeacherSectionEntity {

    @EmbeddedId
    private TeacherSectionPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teacherId")
    @JoinColumn(name = "id_teacher", foreignKey = @ForeignKey(name = "fk_teacher_section_t"))
    private TeacherEntity teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sectionId")
    @JoinColumn(name = "id_section", foreignKey = @ForeignKey(name = "fk_teacher_section_s"))
    private SectionEntity section;


}
