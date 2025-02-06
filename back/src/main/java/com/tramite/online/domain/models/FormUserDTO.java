package com.tramite.online.domain.models;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormUserDTO {


    private Long id;

    @NotNull
    @NotEmpty(message = "Name is not empty")
    private String name;
    
    private String description;
    private Set<SectionDTO> sections;
    
}
