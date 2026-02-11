package com.example.dto;

import java.time.LocalDate;

public record EventFindAll(Long eventId, String name, String description, String location, LocalDate eventDate) {
}