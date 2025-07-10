package com.tramite.online.domain.models;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) representing a workspace.
 * This class is used to transfer data related to a workspace, including its
 * ID, name, description, active status, owner, and associated forms.
 * It includes validation annotations to ensure data integrity.
 */
public class WorkspaceDTO {
    
    private Long id;


    @NotBlank(message="Name is required")
    @Size(max = 50,message = "Name must not exceed 50 characters")
    private String name;
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    
    private Boolean isActive;
    
    private UserDTO owner;
    
    private Set<FormUserDTO> forms;

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

   public Boolean getActive() {
      return isActive;
   }

   public void setActive(Boolean active) {
      isActive = active;
   }

   public UserDTO getOwner() {
      return owner;
   }

   public void setOwner(UserDTO owner) {
      this.owner = owner;
   }

   public Set<FormUserDTO> getForms() {
      return forms;
   }

   public void setForms(Set<FormUserDTO> forms) {
      this.forms = forms;
   }
}
