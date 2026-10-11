package com.swicthly.api.service;


import com.swicthly.api.exception.NotFoundException;
import com.swicthly.api.model.Organization;
import com.swicthly.api.repository.Organizationrepository;
import org.springframework.stereotype.Service;

import java.util.List;
import  java.util.UUID;

@Service
public class Organizationservice {

    private final Organizationrepository organizationrepository;

    public Organizationservice(Organizationrepository organizationrepository){
        this.organizationrepository = organizationrepository;
    }

    public Organization create(String name){
        Organization organization = new Organization(UUID.randomUUID(), name);
        return organizationrepository.save(organization);
    }

    public Organization getById(UUID id) throws NotFoundException {
        return organizationrepository.findById(id).orElseThrow(() -> new NotFoundException("Organization " + id + " not found"));
    }

    public List<Organization> getAll() {
        return organizationrepository.findAll();
    }


}
