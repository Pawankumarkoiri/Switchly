package com.swicthly.api.service;

import com.swicthly.api.exception.ConflictException;
import com.swicthly.api.exception.NotFoundException;
import com.swicthly.api.model.Flag;
import com.swicthly.api.model.Project;
import com.swicthly.api.repository.Flagrepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Flagservice {

    private final Projectservice projectservice;
    private final Flagrepository flagrepository;

    public Flagservice(Projectservice projectservice, Flagrepository flagrepository) {
        this.projectservice = projectservice;
        this.flagrepository = flagrepository;
    }

    public Flag create(UUID projectId, String key, String name,  String description) {
        Project project = projectservice.getById(projectId);  // 404 if it doesn't exist

        if (flagrepository.existsByProjectIdAndKey(projectId, key)) {
            throw new ConflictException("A flag with key '" + key + "' already exists in this project");
        }

        Flag flag = new Flag(UUID.randomUUID(), project.getOrganizationId(), project.getId(), key, name, description, false);
        return flagrepository.save(flag);
    }

    public Flag getById(UUID id) {
        return flagrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flag " + id + " not found"));
    }

    public List<Flag> getAllForProject(UUID projectId) {
        projectservice.getById(projectId);  // 404 if it doesn't exist
        return flagrepository.findByProjectId(projectId);
    }

    public Flag setEnabled(UUID flagId, boolean enabled) {
        Flag flag = getById(flagId);
        flag.setEnabled(enabled);
        return flagrepository.save(flag);
    }

    public void delete(UUID flagId){
        getById(flagId);

        flagrepository.deleteById(flagId);
    }
}
