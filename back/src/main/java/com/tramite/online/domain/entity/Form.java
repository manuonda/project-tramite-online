package com.tramite.online.domain.entity;

import com.tramite.online.audit.Auditable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "forms")
@Getter
@Setter
@Builder
public class Form extends Auditable<String>{
    
}
