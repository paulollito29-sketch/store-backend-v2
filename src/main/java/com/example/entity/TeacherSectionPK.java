package com.example.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode

public class TeacherSectionPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_teacher", foreignKey = @ForeignKey(name = "fk_teacher_section_t"))
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "id_section", foreignKey = @ForeignKey(name = "fk_teacher_section_s"))
    private SectionEntity section;

}
