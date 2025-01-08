package com.tramite.online.controller;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties.Postgresql;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.github.dockerjava.api.model.ContainerConfig;
import com.tramite.online.domain.dto.SectionDTO;
import com.tramite.online.domain.entity.Section;
import com.tramite.online.service.SectionService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(ContainerConfig.class)
public class SectionControllerTest {


   
    @Autowired
    private SectionService sectionService;


    @Autowired
    private TestRestTemplate testRestTemplate;

    SectionDTO sectionDTO;


    @BeforeEach
    public void setUp(){
      sectionDTO = new SectionDTO();

    }


    @Test 
    @DisplayName("Save Section")
    public void givenObjectSection_whenSaveSection_thenReturnObject(){
        // Given
        
        // When
        
        // Then
    }

    @Test
    @DisplayName("Update Section")
    public void given_when_then(){

    }

    @Test
    @DisplayName("Find By Id")
    public void given_when_then(){

    }


    @Test
    @DisplayName("Find All with pagination")
    public void give_when_then(){
        // Given
        
        // When
        
        // Then
    }


}
