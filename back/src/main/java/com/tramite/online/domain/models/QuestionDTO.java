package com.tramite.online.domain.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class QuestionDTO {

    private Long id;
    private String name;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private String questionType;

    private SectionDTO sectionDTO;

    

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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public SectionDTO getSectionDTO() {
        return sectionDTO;
    }

    public void setSectionDTO(SectionDTO sectionDTO) {
        this.sectionDTO = sectionDTO;
    }


    
}
