package com.example.CloudeTask.entity;

import com.example.CloudeTask.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString(exclude = {"company", "user"})
@NoArgsConstructor
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    public Project(String title, String description, ProjectStatus status, Company company, User user) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.company = company;
        this.user = user;
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDate.now();
    }

    public void update(String title, String description, ProjectStatus status, Company company, User user) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.company = company;
        this.user = user;
        this.updatedAt = LocalDate.now();
    }
}
