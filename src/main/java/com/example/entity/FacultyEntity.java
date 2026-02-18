package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "Faculties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class FacultyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFaculty;
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "speciality_id", foreignKey = @ForeignKey(name = "fk_faculty_speciality"))
    private List<SpecialityEntity> speciality;

    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
