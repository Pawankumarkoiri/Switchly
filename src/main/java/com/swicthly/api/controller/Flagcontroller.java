package com.swicthly.api.controller;

import com.swicthly.api.dto.CreateFlagRequest;
import com.swicthly.api.model.Flag;
import com.swicthly.api.service.Flagservice;
import com.switchly.api.dto.UpdateFlagStateRequest;

import org.springframework.web.bind.annotation.DeleteMapping;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class Flagcontroller {

    private final Flagservice flagservice;

    public Flagcontroller(Flagservice flagService) {
        this.flagservice = flagService;
    }

    @PostMapping("/projects/{projectId}/flags")
    @ResponseStatus(HttpStatus.CREATED)
    public Flag create(@PathVariable UUID projectId, @Valid @RequestBody CreateFlagRequest request) {
        return flagservice.create(projectId, request.key(), request.name(), request.description());
    }

    @GetMapping("/projects/{projectId}/flags")
    public List<Flag> getAllForProject(@PathVariable UUID projectId) {
        return flagservice.getAllForProject(projectId);
    }

    @GetMapping("/flags/{flagId}")
    public Flag getById(@PathVariable UUID flagId) {
        return flagservice.getById(flagId);
    }

    @PutMapping("/flags/{flagId}/state")
    public Flag setState(@PathVariable UUID flagId, @Valid @RequestBody UpdateFlagStateRequest request) {
        return flagservice.setEnabled(flagId, request.enabled());
    }

    @DeleteMapping("/flags/{flagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID flagId){
        flagservice.delete(flagId);
    }
}
