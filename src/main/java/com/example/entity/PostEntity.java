package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Posts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
}
