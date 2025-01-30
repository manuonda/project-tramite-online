package com.tramite.online.domain.models;

import java.util.Set;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormUserDTO {

    private Long id;
    private String name;
    private String description;
    private Set<SectionDTO> sections;
    
}
