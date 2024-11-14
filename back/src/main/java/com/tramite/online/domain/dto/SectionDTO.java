package com.tramite.online.domain.dto;

import java.util.Set;

import com.tramite.online.domain.type.SectionType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionDTO {

     private Long id;
     private String name;
     private String description;
     private Boolean enabled;

     @Enumerated(EnumType.STRING)
     private SectionType sectionType;

    private Set<QuestionDTO> questions;

}
