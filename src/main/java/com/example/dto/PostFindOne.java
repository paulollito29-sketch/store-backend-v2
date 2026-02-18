package com.example.dto;

import java.util.List;

public record PostFindOne(Long postId, String title, Integer reactions, Double rating) {
}
