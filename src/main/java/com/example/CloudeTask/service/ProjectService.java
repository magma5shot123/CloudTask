package com.example.CloudeTask.service;

import com.example.CloudeTask.dto.project.CreateNewProjectRequest;
import com.example.CloudeTask.dto.project.ProjectResponse;
import com.example.CloudeTask.dto.project.UpdateProjectRequest;
import com.example.CloudeTask.entity.Company;
import com.example.CloudeTask.entity.Project;
import com.example.CloudeTask.entity.User;
import com.example.CloudeTask.repository.CompanyRepository;
import com.example.CloudeTask.repository.ProjectRepository;
import com.example.CloudeTask.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;

    public ProjectService(UserRepository userRepository, CompanyRepository companyRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
    }

    public ProjectResponse createNewProject(CreateNewProjectRequest dto) {

        Company company = companyRepository.findById(dto.companyId())
                .orElseThrow(() -> new IllegalArgumentException("Company Not Found"));

        User user = userRepository.findById(dto.userId())
                        .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        Project project = new Project(
                dto.title(),
                dto.description(),
                dto.status(),
                company,
                user
        );

        projectRepository.save(project);

        return mapToProject(project);
    }

    @Transactional
    public ProjectResponse updateProject(Long id, UpdateProjectRequest dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        Company company = companyRepository.findById(dto.companyId())
                .orElseThrow(() -> new IllegalArgumentException("Company Not Found"));
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.update(
                dto.title(),
                dto.description(),
                dto.status(),
                company,
                user
        );

        return mapToProject(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project Not Found"));

        projectRepository.delete(project);
    }

    @Transactional
    public void deleteAllCompanyProject(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company Not Found");
        }

        projectRepository.deleteAllProjectByCompanyId(id);
    }

    private ProjectResponse mapToProject(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getStatus(),
                project.getCompany().getId(),
                project.getUser().getId(),
                project.getCreatedAt()
        );
    }
}
