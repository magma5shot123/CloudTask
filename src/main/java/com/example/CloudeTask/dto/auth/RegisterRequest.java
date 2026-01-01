package com.example.CloudeTask.dto.auth;

import com.example.CloudeTask.entity.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest (
        @NotBlank(message = "Username can't be blank")
        String username,

        @Email
        @NotBlank(message = "Email can't be blank")
        String email,

        @NotBlank
        @Size(min = 8, max = 20, message = "Invalid password")
        String password,

        @NotBlank
        @Size(min = 8, max = 20, message = "Invalid password")
        String secondPassword,

        @NotNull(message = "Company ID can't be null")
        Long companyId
) {
}
