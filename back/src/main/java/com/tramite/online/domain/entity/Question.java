package com.tramite.online.domain.entity;

import com.tramite.online.audit.Auditable;
import com.tramite.online.domain.type.QuestionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="questions")
public class Question  extends  Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_question")
    private Long id;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="question_type")
    private QuestionType questionType;


    @ManyToOne
    @JoinColumn(name="section_id", nullable=false)
    private Section section;


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


    public QuestionType getQuestionType() {
        return questionType;
    }


    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }


    public Section getSection() {
        return section;
    }


    public void setSection(Section section) {
        this.section = section;
    }

    

}
