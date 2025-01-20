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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="questions")
public class Question  extends  Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;


    @ManyToOne
    @JoinColumn(name="section_id", nullable=false)
    private Section section;

}
