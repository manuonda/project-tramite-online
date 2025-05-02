
package com.tramite.online.domain.models;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) representing a form user.
 * This class is used to transfer data related to a form user, including their
 * ID, name, description, and associated sections.
 * It includes validation annotations to ensure data integrity.
 */
public class FormUserDTO {


    private Long id;

    @NotNull
    @NotEmpty(message = "Name is not empty")
    private String name;
    
    private String description;
    private Set<SectionDTO> sections;




    public FormUserDTO() {
    }
    
    public FormUserDTO(Long id, @NotNull @NotEmpty(message = "Name is not empty") String name, String description,
            Set<SectionDTO> sections) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sections = sections;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<SectionDTO> getSections() {
        return sections;
    }
    public void setSections(Set<SectionDTO> sections) {
        this.sections = sections;
    }

    
    
}
