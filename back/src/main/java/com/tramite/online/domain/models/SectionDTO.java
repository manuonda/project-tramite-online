package com.tramite.online.domain.models;

import java.util.Set;

import com.tramite.online.domain.type.SectionType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class SectionDTO {

     private Long id;
     
     @NotNull
     @NotEmpty(message = "Not empty Name")
     private String name;

     private String description;
     
     private Boolean enabled;

     @Enumerated(EnumType.STRING)
     private SectionType sectionType;


     
     
     public SectionDTO() {
    }

     public SectionDTO(Long id, @NotNull @NotEmpty(message = "Not empty Name") String name, String description,
            Boolean enabled, SectionType sectionType, Set<QuestionDTO> questions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.sectionType = sectionType;
        this.questions = questions;
    }

     private Set<QuestionDTO> questions;

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

     public Boolean getEnabled() {
          return enabled;
     }

     public void setEnabled(Boolean enabled) {
          this.enabled = enabled;
     }

     public SectionType getSectionType() {
          return sectionType;
     }

     public void setSectionType(SectionType sectionType) {
          this.sectionType = sectionType;
     }

     public Set<QuestionDTO> getQuestions() {
          return questions;
     }

     public void setQuestions(Set<QuestionDTO> questions) {
          this.questions = questions;
     }

     
}
