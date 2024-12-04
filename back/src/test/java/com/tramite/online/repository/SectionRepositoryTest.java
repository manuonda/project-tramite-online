package com.tramite.online.repository;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tramite.online.domain.entity.Section;

@DataJpaTest
public class SectionRepositoryTest {

    @Autowired
    private SectionRepository sectionRepository;

    private Section section;
    @BeforeEach
    public void init(){

    }


    @Test
    @DisplayName("Add Section")
    public void givenSection_whenSaveEntity_thenReturnObjectSection(){
        //given
        //when
        //then
    }

}
