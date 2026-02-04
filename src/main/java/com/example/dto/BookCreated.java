package com.example.dto;

public record BookCreated(Long idBook, String title, String description, Double price, Integer stock) {
}
