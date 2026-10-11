package com.swicthly.api.repository;

import com.swicthly.api.model.Organization;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface Organizationrepository {

    Organization save(Organization organization);

    Optional<Organization> findById(UUID id);

    List<Organization> findAll();
}
