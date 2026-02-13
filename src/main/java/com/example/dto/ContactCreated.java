package com.example.dto;

public record ContactCreated(Long contactId, String name, String phone, String email, String address) {
}