package com.swicthly.api.controller;

import com.swicthly.api.dto.CreateProjectRequest;
import com.swicthly.api.model.Project;
import com.swicthly.api.service.Projectservice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class Projectcontroller {

    private final Projectservice projectservice;

    public Projectcontroller(Projectservice projectService) {
        this.projectservice = projectService;
    }

    @PostMapping("/orgs/{orgId}/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@PathVariable UUID orgId, @Valid @RequestBody CreateProjectRequest request) {
        return projectservice.create(orgId, request.name());
    }

    @GetMapping("/orgs/{orgId}/projects")
    public List<Project> getAllForOrganization(@PathVariable UUID orgId) {
        return projectservice.getAllForOrganization(orgId);
    }

    @GetMapping("/projects/{projectId}")
    public Project getById(@PathVariable UUID projectId) {
        return projectservice.getById(projectId);
    }
}
