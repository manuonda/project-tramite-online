package com.tramite.online.domain.entity;

import java.util.Set;

import com.tramite.online.audit.Auditable;
import com.tramite.online.config.security.model.TypeProvider;
import com.tramite.online.domain.type.QuestionType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name="users")
@ToString(exclude ="formUsers")
public class User extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Not Empty User Name")
    @Column(name = "user_name",nullable = false)
    private String userName;

    @NotBlank(message =  "Email not Empty")
    @Column(name="email", nullable = false)
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(name="provider_type")
    private TypeProvider provider;


    @Column(name ="provider_id")
    private String providerId;

    private String password;


    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<FormUser> formUsers;


}
