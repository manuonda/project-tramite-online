package com.tramite.online.service;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.DynamicTest.stream;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nimbusds.jose.util.Resource;
import com.tramite.online.domain.dto.SectionDTO;
import com.tramite.online.domain.entity.Section;
import com.tramite.online.domain.type.SectionType;
import com.tramite.online.exception.ResourceFound;
import com.tramite.online.exception.ResourceNotFound;
import com.tramite.online.repository.SectionRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.assertThat;




@ExtendWith(MockitoExtension.class)
public class SectionServiceTest {

    private static final String TITLE = "section one";

    @Mock
    private SectionRepository sectionRepository;

    @InjectMocks
    private SectionService service;

    Section section;
    SectionDTO sectionDTO;

    @BeforeEach
    public void init() {
        sectionDTO = new SectionDTO();
        sectionDTO.setName(TITLE);
        sectionDTO.setDescription("Description one");
        sectionDTO.setEnabled(Boolean.TRUE);
        sectionDTO.setSectionType(SectionType.SECTION_PANELS);

        section = new Section().builder()
        .name(TITLE)
        .description("Description")
        .enabled(Boolean.TRUE)
        .sectionType(SectionType.SECTION_WIZARD)
        .build();
    }

    @Test
    @DisplayName("Save Section")
    public void givenObjectSection_whenSaveObject_thenReturnObject() {
        // given
        section = SectionService.toSection(sectionDTO);
        when(this.sectionRepository.findByName(TITLE)).thenReturn(Optional.empty());
        when(this.sectionRepository.save(section)).thenReturn(section);

        // when
        SectionDTO result = this.service.save(sectionDTO);

        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo(TITLE);
        verify(sectionRepository).findByName(TITLE);
        verify(sectionRepository).save(any(Section.class));
    }

    @Test
    @DisplayName("Delete Section Logic")
    void givenObjectSection_whenDeleteSection_thenReturnEmptyObject() {
        // given
        Section section = new Section();
        section.setId(1L);
        when(this.sectionRepository.findById(section.getId())).thenReturn(Optional.of(section));
        when(this.sectionRepository.save(any(Section.class))).thenReturn(section);

        // when
        this.service.delete(1L);

        // then
        Assertions.assertThat(section.getEnabled()).isFalse();
        verify(sectionRepository).findById(1L);
        verify(sectionRepository).save(section);
    }

    @Test
    @DisplayName("Exception exist name section")
    public void givenExistingNameSection_whenSaveSection_thenThrowsException(){
        //given 
        Section section = new Section()
        .builder()
        .name(TITLE)
        .description("Description one")
        .build();
         when(this.sectionRepository.findByName(TITLE)).thenReturn(Optional.of(section));

        //when 
        org.junit.jupiter.api.Assertions.assertThrows(ResourceFound.class, ()->{
            service.save(sectionDTO);
        });

        //then
        verify(this.sectionRepository,times(1)).findByName(TITLE);
        verify(this.sectionRepository, never()).save(any(Section.class));
    }

    @Test
    @DisplayName("Get Section Not Found by Id")
    public void givenIdSection_whenFindById_ReturnObjectEmpty(){
       
        when(this.sectionRepository.findById(anyLong())).thenReturn(Optional.empty());
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFound.class, () -> {
            this.service.getById(1L);
        }, "Section not found with the given ID");
      
        verify(sectionRepository).findById(1L);
    }
    @Test
    @DisplayName("Should find section by id")
    public void givenSection_whenFindById_thenReturnObject() {
        // given
        Long id = 1L;
        section.setId(id);
        when(sectionRepository.findById(id)).thenReturn(Optional.of(section));
    
        // when
        SectionDTO result = service.getById(id);  // Call service method instead of static method
    
        // then 
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo(TITLE);
        Assertions.assertThat(result.getId()).isEqualTo(id);
        verify(sectionRepository).findById(id);
    }

    @Test
    @DisplayName("Section Test Update")
    public void givenSection_whenUpdate_thenReturnObject(){
        //given
        Long id = 1L;
        section.setId(1L);
        String UPDATE_SECTION="Update Section";
        
        sectionDTO.setId(id);
        sectionDTO.setName(UPDATE_SECTION);
        
        when(this.sectionRepository.findById(anyLong())).thenReturn(Optional.of(section));
        when(this.sectionRepository.findByName(UPDATE_SECTION)).thenReturn(Optional.empty());
        when(this.sectionRepository.save(any(Section.class))).thenReturn(section);
        
        //when
        SectionDTO result = this.service.update(id, sectionDTO);
        
        //then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(UPDATE_SECTION);
        assertThat(result.getId()).isEqualTo(id);
        verify(sectionRepository).findById(id);
        verify(sectionRepository).findByName(UPDATE_SECTION);
        verify(sectionRepository).save(any(Section.class));

    }

    // TODO: find all sections pagination
    // @Test
    // @DisplayName("Find All Sections")
    // void testFindAll() {
    //     // given
    //     Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
    //     Page<Section> page = Page.empty(pageable);
    //     when(this.sectionRepository.findAll(pageable)).thenReturn(page);

    //     // when
    //     PagedResult<SectionDTO> result = this.service.findAll(0, 10, "ASC", sectionDTO);

    //     // then
    //     Assertions.assertThat(result).isNotNull();
    //     verify(sectionRepository).findAll(pageable);
    // }


}
