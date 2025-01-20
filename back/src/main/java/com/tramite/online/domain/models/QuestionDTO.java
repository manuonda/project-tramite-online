package com.tramite.online.domain.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {

    private Long id;
    private String name;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private String questionType;

    private SectionDTO sectionDTO;
}
