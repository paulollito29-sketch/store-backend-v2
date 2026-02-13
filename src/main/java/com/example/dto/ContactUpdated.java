package com.example.dto;

public record ContactUpdated(Long contactId, String name, String phone, String email, String address) {
}