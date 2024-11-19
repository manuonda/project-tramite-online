package com.tramite.online.domain.dto;

import com.tramite.online.domain.type.SectionType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionDTO {

     private Long id;
     @NotNull
     @NotEmpty(message = "Not empty Name")
     private String name;

     private String description;
     
     private Boolean enabled;

     @Enumerated(EnumType.STRING)
     private SectionType sectionType;
}
