package com.example.CloudeTask.controller;

import com.example.CloudeTask.dto.project.CreateNewProjectRequest;
import com.example.CloudeTask.dto.project.ProjectResponse;
import com.example.CloudeTask.dto.project.UpdateProjectRequest;
import com.example.CloudeTask.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping()
    public ProjectResponse createProject(@Valid @RequestBody CreateNewProjectRequest dto) {
        return projectService.createNewProject(dto);
    }

    @PutMapping("/{id}")
    public ProjectResponse updateProject(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProjectRequest dto) {
        return projectService.updateProject(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

    @DeleteMapping("/company/{companyId}")
    public void deleteAllProjectCompany(@PathVariable Long companyId) {
        projectService.deleteAllCompanyProject(companyId);
    }
}
