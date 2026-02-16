package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Notifications")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;
    private String body;
    private String title;
}

