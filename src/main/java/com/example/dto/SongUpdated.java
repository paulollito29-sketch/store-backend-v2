package com.example.dto;

public record SongUpdated(Long songId, String name, String artist, String genre, Integer duration) {
}
