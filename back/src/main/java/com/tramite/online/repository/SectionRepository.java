package com.tramite.online.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.tramite.online.domain.entity.Section;

public interface SectionRepository  extends JpaRepository<Section, Long>, QueryByExampleExecutor<Section>{

    Optional<Section>  findByName(String name);
}
