package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Movies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idMovie;
    private String name;
    private String director;
    private Integer duration;
    private Integer year;
    private String genre;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
