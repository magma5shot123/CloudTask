package com.example.CloudeTask.repository;

import com.example.CloudeTask.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Long> {
    void deleteAllProjectByCompanyId(Long companyId);
}
