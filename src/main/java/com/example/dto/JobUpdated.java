package com.example.dto;

public record JobUpdated(Long jobId, String name, Double minSalary, Double maxSalary, Integer maxEmployee) {
}
