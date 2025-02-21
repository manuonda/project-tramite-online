package com.tramite.online.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tramite.online.domain.entity.Section;

public interface SectionRepository  extends JpaRepository<Section, Long>{

    Optional<Section>  findByName(String name);
}
