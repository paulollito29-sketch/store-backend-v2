package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Animals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idAnimal;
    private String raza;
    private String name;
    private Integer age;
    private Double weight;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
