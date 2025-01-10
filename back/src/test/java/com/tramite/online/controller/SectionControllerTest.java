package com.tramite.online.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tramite.online.TestContainerConfiguration;
import com.tramite.online.domain.dto.SectionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;


import static org.assertj.core.api.Assertions.*;
//https://fullstackcode.dev/2025/01/08/exploring-the-assertj-integration-and-new-mockmvctester-class-in-spring-boot-3-4/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
@AutoConfigureMockMvc
public class SectionControllerTest {

    @Autowired
    private MockMvcTester mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private SectionDTO sectionDTO;

    @BeforeEach
    public void setUp() {
        sectionDTO = new SectionDTO();
        sectionDTO.setName("Test Section");
        sectionDTO.setDescription("This is a test section description");
        sectionDTO.setEnabled(Boolean.TRUE); 
     }

    @Test
    @DisplayName("Save Section")
    public void givenObjectSection_whenSaveSection_thenReturnObject() throws Exception {
        // Given
        String sectionJson  = objectMapper.writeValueAsString(this.sectionDTO);

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
       
        var json =  result.getResponse().getContentAsString();
        assertThatObject(JsonPath.read(json, "name")).isEqualTo(sectionDTO.getName());
        assertThatObject(JsonPath.read(json, "description")).isEqualTo(sectionDTO.getDescription());
        assertThat((Integer) JsonPath.read(json,"id")).isGreaterThan(0);

    }
}
