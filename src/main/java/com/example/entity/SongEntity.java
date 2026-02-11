package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name="Songs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSong;
    private String name;
    private String artist;
    private Integer duration;
    private String genre;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
