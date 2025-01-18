package com.tramite.online.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tramite.online.TestContainerConfiguration;
import com.tramite.online.domain.models.SectionDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.assertArg;

//https://fullstackcode.dev/2025/01/08/exploring-the-assertj-integration-and-new-mockmvctester-class-in-spring-boot-3-4/
//https://github.com/springframeworkguru/spring-6-rest-mvc/blob/178-test-with-docker-compose/src/main/java/guru/springframework/spring6restmvc/services/BeerOrderServiceJPA.java
//https://github.com/sureshgadupu/mockmvctester-demo/blob/main/src/test/java/dev/fullstackcode/mockmvctester/demo/EmpMockMvcTesterTest.java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
@AutoConfigureMockMvc
public class SectionControllerTest {

    @Autowired
    private MockMvcTester mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private SectionDTO sectionDTO;

    private SectionDTO savSectionDTO;

    @BeforeEach
    public void setUp() {
        sectionDTO = new SectionDTO();
        sectionDTO.setName("Test Section");
        sectionDTO.setDescription("This is a test section description");
        sectionDTO.setEnabled(Boolean.TRUE);
    }

    @Test
    @DisplayName("Save Section")
    @Order(1)
    public void givenObjectSection_whenSaveSection_thenReturnObject() throws Exception {
        // Given
        String sectionJson = objectMapper.writeValueAsString(this.sectionDTO);

        // When & Then
        var result = this.mockMvc.post()
                .uri("/api/v1/section")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(sectionJson)
                .exchange();

      
        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);

        var json = result.getResponse().getContentAsString();
        assertThatObject(JsonPath.read(json, "name")).isEqualTo(sectionDTO.getName());
        assertThatObject(JsonPath.read(json, "description")).isEqualTo(sectionDTO.getDescription());
        assertThat((Integer) JsonPath.read(json, "id")).isGreaterThan(0);

    }

    @Test
    @DisplayName("List Section DTO")
    public void givenObjectSection_whenFind_thenReturnListObject(){

        MvcTestResult mockMvc = this.mockMvc.get().uri("/api/v1/section").exchange();
        
        assertThat(mockMvc)
        .hasStatusOk()
        .hasContentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE);

        
        assertThat(mockMvc.getResponse().getStatus()).isEqualTo(200);
        assertThat(mockMvc.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);


     }

    @Test
    @DisplayName("Find Section by Id")
    @Order(2)
    public void given_whenFindById_thenReturnObjectSection() throws Exception {

        // given
        String sectionJson = objectMapper.writeValueAsString(this.sectionDTO);

        // When & Then
        var result = this.mockMvc.post()
                .uri("/api/v1/section")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(sectionJson)
                .exchange();

        savSectionDTO = this.objectMapper.readValue(
                result.getResponse().getContentAsString(), SectionDTO.class);

        // Type 1
        assertThat(
                this.mockMvc.get().uri("/api/v1/section/" + savSectionDTO.getId()))
                .hasContentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE)
                .bodyJson()
                .extractingPath("$")
                .asMap()
                .contains(
                        entry("name", savSectionDTO.getName()),
                        entry("description", savSectionDTO.getDescription()));

        // Type 2
        assertThat(this.mockMvc.get().uri("/api/v1/section/" + savSectionDTO.getId()))
        .hasContentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE)
        .bodyJson()
        .extractingPath("$")
        .convertTo(SectionDTO.class)
        .satisfies(e -> assertThat(e.getName()).isEqualTo(savSectionDTO.getName()))
        .satisfies(e -> assertThat(e.getDescription()).isEqualTo(savSectionDTO.getDescription()));
      
        // Type 3
        MvcTestResult response = this.mockMvc.get().uri("/api/v1/section/" + savSectionDTO.getId()).exchange();
        assertThat(response.getResponse().getStatus()).isEqualTo(200);
        assertThat(response.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);

        var json = response.getResponse().getContentAsString();
        SectionDTO resultJson = this.objectMapper.readValue(json,SectionDTO.class); 

        assertThatObject(JsonPath.read(json, "name")).isEqualTo(savSectionDTO.getName());
        assertThatObject(JsonPath.read(json,"description")).isEqualTo(savSectionDTO.getDescription());
        
        assertThatObject(resultJson.getName()).isEqualTo(savSectionDTO.getName());
        assertThat(this.savSectionDTO.getId()).isGreaterThan(0);
    
    }
}
