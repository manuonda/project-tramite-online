package com.tramite.online.domain.entity;

import java.util.HashSet;
import java.util.Set;

import com.tramite.online.audit.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



/**
 * Table correspondiente to Workspace 
 * @author : dgarcia
 * @version : 1.0 
 * @date : 5/7/2025
 */
@Entity
@Table(name="workspace")
public class WorkSpace extends Auditable<String>{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable=false , length=50)
    @NotBlank(message="Name is required")
    private String name;

    @Size(max=500 , message="Description")
    @Column(name="description")
    private String description;

    @Column(name="is_active")
    private Boolean isActive=true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user")
    private User owner;

    @OneToMany(mappedBy="workspace",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<FormUser> forms =new HashSet<>();



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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<FormUser> getForms() {
        return forms;
    }

    public void setForms(Set<FormUser> forms) {
        this.forms = forms;
    }

}
