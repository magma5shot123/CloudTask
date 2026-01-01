package com.example.CloudeTask.dto.project;

import com.example.CloudeTask.enums.ProjectStatus;

import java.time.LocalDate;

public record ProjectResponse(
        Long id,
        String title,
        String description,
        ProjectStatus status,
        Long companyId,
        Long userId,
        LocalDate createdAt
) {
}
