package com.example.CloudeTask.dto.project;

import com.example.CloudeTask.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UpdateProjectRequest(

        @NotBlank(message = "Title can't be blank")
        String title,

        @NotBlank(message = "Description can't be blank")
        String description,

        @NotNull(message = "Status can't be null")
        ProjectStatus status,

        @NotNull(message = "Company ID can't be null")
        Long companyId,

        @NotNull(message = "User ID can't be null")
        Long userId
) {
}
