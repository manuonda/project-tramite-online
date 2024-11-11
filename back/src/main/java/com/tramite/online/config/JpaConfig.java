package com.tramite.online.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
 * @Configuration: Indicates that this class is a source 
 * of bean definitions for the Spring application context.
   @EnableJpaAuditing(auditorAwareRef = "auditorProvider"): 
   Enables JPA auditing, which allows for automatic tracking of changes to entities, 
   and references an auditorProvider 
   bean that provides the auditor (e.g., the user making the changes).
 */

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {


       // Definir un bean AuditorAware para proporcionar el auditor actual
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable("Admin"); // Esto es un ejemplo, se puede reemplazar con el usuario actual
    }

    /* 
    @Bean
    public AuditorAware<String> auditorProvider() {
       return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    */

}
