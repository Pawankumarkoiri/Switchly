package com.swicthly.api.controller;

import com.swicthly.api.dto.CreateOrganizationRequest;
import com.swicthly.api.model.Organization;
import com.swicthly.api.service.Organizationservice;
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
@RequestMapping("/api/v1/orgs")
public class Organizationcontroller {

    private final Organizationservice organizationservice;
    public Organizationcontroller(Organizationservice organizationservice){
        this.organizationservice =organizationservice;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Organization create(@Valid @RequestBody CreateOrganizationRequest request){
        return organizationservice.create(request.name());
    }

    @GetMapping
    public List<Organization> getAll() {
        return organizationservice.getAll();
    }

    @GetMapping("/{orgId}")
    public Organization getById(@PathVariable UUID orgId) throws ClassNotFoundException {
        return organizationservice.getById(orgId);
    }
}
