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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "seccion")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Section extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false , length=50)
    private String name;

    @Column(name="description", nullable=true , length=100)
    private String description;

    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private SectionType sectionType;

    @OneToMany(mappedBy = "section", fetch= FetchType.LAZY)
    @JoinColumn(columnDefinition="seccion_id")
    private Set<Question> questions;

}
