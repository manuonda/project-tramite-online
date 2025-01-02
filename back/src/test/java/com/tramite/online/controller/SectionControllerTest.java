package com.tramite.online.controller;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties.Postgresql;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.tramite.online.service.SectionService;


@SpringBootTest
@Testcontainers
public class SectionControllerTest {


    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> container = new PostgreSQLContainer<>(
        DockerImageName.parse("postgres:latest")
    );

    @Autowired
    private SectionService sectionService;

    @BeforeEach
    public void setUp(){

    }

    @Test
    @DisplayName("Find All with pagination")
    public void give_when_then(){
        // Given

        // When
        
        // Then
    }
}
