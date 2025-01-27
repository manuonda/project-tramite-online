package com.tramite.online.domain.models;

import java.util.Set;

import com.tramite.online.domain.entity.Question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormDTO {

    private Long id;
    private String name;
    private String description;
    private Set<SectionDTO> sections;
    
}
