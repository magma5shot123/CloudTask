package com.example.CloudeTask.repository;

import com.example.CloudeTask.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
