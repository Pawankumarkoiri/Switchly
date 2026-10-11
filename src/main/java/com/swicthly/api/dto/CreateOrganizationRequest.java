package com.swicthly.api.dto;

import jakarta.validation.constraints.NotBlank;


public record CreateOrganizationRequest(@NotBlank String name){

}

