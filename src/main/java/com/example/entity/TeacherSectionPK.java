package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TeacherSectionPK implements Serializable {

    @Column(name = "id_teacher")
    private Long teacherId;


    @Column(name = "id_section")
    private Long sectionId;
}
