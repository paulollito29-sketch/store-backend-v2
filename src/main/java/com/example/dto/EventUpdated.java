package com.example.dto;

import java.time.LocalDate;

public record EventUpdated(Long eventId, String name, String description, String location, LocalDate eventDate) {
}
