package com.example.dto;

import java.time.LocalDate;

public record EventFindOne(Long eventId, String name, String description, String location, LocalDate eventDate) {
}
