package com.tramite.online.domain.entity;

import java.util.Set;

import com.tramite.online.audit.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "form_users")
@ToString(exclude = "sections")
@EqualsAndHashCode(exclude = "sections")
public class FormUser extends Auditable<String>{
    
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull(message ="Name is required")
    @Max(value = 50, message = "Name must be less than 50 characters")
    private String name;

    private String description;

    @OneToMany(mappedBy = "form" , cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Section> sections;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "id_user")
    private User user;
    
}
