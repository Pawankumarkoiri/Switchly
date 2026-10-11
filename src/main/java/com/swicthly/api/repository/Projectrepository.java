package com.swicthly.api.repository;

import com.swicthly.api.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface  Projectrepository{

    Project save(Project project);

    Optional<Project> findById(UUID id);

    List<Project> findByOrganizationId(UUID organizationId);
}
