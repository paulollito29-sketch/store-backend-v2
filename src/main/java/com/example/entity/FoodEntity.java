package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Foods")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFood;
    private String type;
    private Double price;
    private String name;
    private String ingredients;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
