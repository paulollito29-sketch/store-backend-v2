package com.example.dto;

public record SongCreated(Long songId, String name, String artist, String genre, Integer duration) {
}
