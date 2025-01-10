package com.tramite.online.controller;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tramite.online.TestContainerConfiguration;
import com.tramite.online.domain.dto.SectionDTO;
import com.tramite.online.service.SectionService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
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
        Assertions.assertThat(testRestTemplate).isNotNull();
        // Given
        HttpEntity<SectionDTO> request = new HttpEntity<>(sectionDTO);  

        // When
        ResponseEntity<SectionDTO> response = testRestTemplate
        .postForEntity("/api/v1/section",request, SectionDTO.class);
          
        // Then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Update Section")
    public void givenObjectSection_whenUpdateSection_thenReturnObject(){

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
