package com.tramite.online.domain.entity;

import java.util.Set;

import com.tramite.online.audit.Auditable;
import com.tramite.online.domain.type.SectionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "sections")
public class Section extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
    generator="section_id_generator")
    @SequenceGenerator(name="section_id_generator" ,
    sequenceName="section_id_seq", allocationSize=1)
    @Column(name="id_section")
    private Long id;

    @Column(name="name", nullable=false , length=50)
    private String name;

    @Column(name="description", nullable=true , length=100)
    private String description;

    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name="section_type")
    private SectionType sectionType;

    @OneToMany(mappedBy = "section", fetch= FetchType.LAZY , orphanRemoval = true)
    private Set<Question> questions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_form_user", nullable = false)
    private FormUser form;


    
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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public FormUser getForm() {
        return form;
    }

    public void setForm(FormUser form) {
        this.form = form;
    }

    

}
