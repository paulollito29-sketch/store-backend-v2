package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "Specialities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class SpecialityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpeciality;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", foreignKey = @ForeignKey(name = "fk_speciality_faculty"))
    private FacultyEntity faculty;

    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
