package com.tramite.online.repository;

import com.tramite.online.domain.entity.Section;
import com.tramite.online.domain.type.SectionType;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;


@DataJpaTest
@TestPropertySource(properties = {
        "spring.test.database.replace=none",
        "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"
})
//@Testcontainers
public class SectionRepositoryTest {


    @Autowired
    private SectionRepository sectionRepository;


    @Test
    @DisplayName("Save Test Section")
    public void giveObjectSection_whenSaveSection_thenReturnObject() {

        //given
        Section section = new Section();
        section.setName("Section one");
        section.setDescription("Description one");
        //when
        Section sectionSave = this.sectionRepository.save(section);

        //then
        Assertions.assertThat(sectionSave).isNotNull();

    }

    @Test
    @DisplayName("Test Update Section")
    public void givenSection_whenUpdate_thenReturnObject(){
        //given
        Section section = new Section(null, "name","description one",true, SectionType.SECTION_WIZARD, null);
        Section sectionSave = this.sectionRepository.save(section);
       
        //when 
        sectionSave.setName("update name");
        sectionSave.setDescription("Description one");
        sectionSave.setEnabled(false);
        sectionSave.setSectionType(SectionType.SECTION_WIZARD);
        Section sectionUpdate = this.sectionRepository.save(sectionSave);
        
        //then
        Assertions.assertThat(sectionUpdate).isNotNull();
        Assertions.assertThat(sectionUpdate.getId()).isEqualTo(sectionSave.getId());
   
    }

    @Test
    @DisplayName("Delete Section")
    public void givenObjectSection_whenDelete_thenReturnEmpty(){
        //given
        Section section = new Section();
        section.setName("SectionOne");
        section.setDescription("Description");
        Section sectionSave = this.sectionRepository.save(section);

        //
        this.sectionRepository.delete(sectionSave);
        Optional<Section> optionalSection = this.sectionRepository.findById(sectionSave.getId());
        
        //then
        Assertions.assertThat(optionalSection).isEmpty();
        
    }
}