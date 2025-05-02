package com.tramite.online.domain.entity;

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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "form_users")
public class FormUser extends Auditable<String>{
    
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    @Column(name="id_form_user")
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

    
    public FormUser() {
    }


    private FormUser( Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.user = builder.user;
    }


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


    public Set<Section> getSections() {
        return sections;
    }


    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "FormUser [id=" + id + ", name=" + name + ", description=" + description + ", sections=" + sections
                + ", user=" + user + "]";
    }


    public static Builder builder(){
        return new Builder();
    }
    public static class Builder {

        private Long id;
        private String name;
        private String description;
        private User user;

        private Builder(){}

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public FormUser build(){
           return new FormUser(this);
        }


    }
    
    
    
}
