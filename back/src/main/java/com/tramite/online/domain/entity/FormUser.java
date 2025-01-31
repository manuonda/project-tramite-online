package com.tramite.online.domain.entity;

import java.util.Set;

import com.tramite.online.audit.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Entity(name = "form_users")
@Data
@Builder
public class FormUser extends Auditable<String>{
    
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull(message ="Name is required")
    @Max(value = 50, message = "Name must be less than 50 characters")
    private String name;

    private String description;

    @OneToMany(mappedBy = "form")
    private Set<Section> sections;
    
}
