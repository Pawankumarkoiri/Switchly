package com.swicthly.api.service;

import org.springframework.stereotype.Service;



import com.swicthly.api.exception.NotFoundException;
import com.swicthly.api.model.Organization;
import com.swicthly.api.model.Project;
import com.swicthly.api.repository.Projectrepository;

import java.util.List;
import java.util.UUID;

@Service
public class Projectservice {

    private final Organizationservice organizationservice;
    private final Projectrepository projectrepository;

    public Projectservice(Organizationservice organizationservice, Projectrepository projectrepository) {
        this.organizationservice = organizationservice;
        this.projectrepository = projectrepository;
    }

    public Project create(UUID organizationId, String name) {
        Organization organization = organizationservice.getById(organizationId);  // 404 if it doesn't exist
        Project project = new Project(UUID.randomUUID(), organization.getId(), name);
        return projectrepository.save(project);
    }

    public Project getById(UUID id) {
        return projectrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project " + id + " not found"));
    }

    public List<Project> getAllForOrganization(UUID organizationId) {
        organizationservice.getById(organizationId);  // 404 if it doesn't exist
        return projectrepository.findByOrganizationId(organizationId);
    }
}
