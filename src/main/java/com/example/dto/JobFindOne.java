package com.example.dto;

public record JobFindOne(Long jobId, String name, Double minSalary, Double maxSalary, Integer maxEmployee) {
}
