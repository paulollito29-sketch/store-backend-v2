package com.example.dto;

import java.time.LocalDate;

public record EventCreated(Long eventId, String name, String description, String location, LocalDate eventDate) {
}