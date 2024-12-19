package com.tramite.online.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tramite.online.domain.dto.PagedResult;
import com.tramite.online.domain.dto.SectionDTO;
import com.tramite.online.domain.entity.Section;
import com.tramite.online.exception.ResourceFound;
import com.tramite.online.exception.ResourceNotFound;
import com.tramite.online.repository.QuestionRepository;
import com.tramite.online.repository.SectionRepository;


import static com.tramite.online.constants.CacheConstants.GET_SECTION_BY_ID;
import static com.tramite.online.constants.CacheConstants.GET_ALL_SECTIONS;


@Service
public class SectionService {

    
    private final SectionRepository sectionRepository;
    private final QuestionRepository questionRepository;

    public SectionService(SectionRepository sectionRepository,
    QuestionRepository questionRepository){
        this.sectionRepository = sectionRepository;
        this.questionRepository = questionRepository;
    }


    //@Cacheable(GET_ALL_SECTIONS)
    public PagedResult<SectionDTO> findAll(int page, int size, String sortDirection,SectionDTO sectionDTO){
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        final Sort sort = Sort.by(direction, "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<SectionDTO> pageSections = this.sectionRepository.findAll(pageable)
        .map(SectionService::toSectionDTO);
    
        return new PagedResult<>(
        pageSections.getContent(), 
        pageSections.getTotalElements(),
        pageSections.getNumber(), 
        pageSections.getTotalPages(), 
        pageSections.isFirst(), 
        pageSections.isLast(), 
        pageSections.hasNext(), 
        pageSections.hasPrevious()
        );
    }


    public SectionDTO save(SectionDTO sectionDTO){
        
        Section section = SectionService.toSection(sectionDTO);
        Optional<Section> sectionFindByName = this.sectionRepository.findByName(section.getName());
        if ( sectionFindByName.isPresent()){
            throw new ResourceFound("Section name already exist");
        }
        Section sectionSave = this.sectionRepository.save(section);
        return SectionService.toSectionDTO(sectionSave);
    }



    /**
     * Method Section <strong> update</strong>
     * @param id
     * @param sectionDTO
     * @return
     */
    public SectionDTO update(Long id, SectionDTO sectionDTO){

        return  this.sectionRepository.findById(id)
          .map(existingSection -> {

            Optional<Section> findByName = this.sectionRepository.findByName(sectionDTO.getName());
            if ( findByName.isPresent() && !findByName.get().getId().equals(sectionDTO.getId())){
               throw new ResourceFound("Section name exist in other Section");
            }

            Section section = SectionService.toSection(sectionDTO);
            System.out.println(section.getName());
            section.setId(sectionDTO.getId());
            Section updateSection = this.sectionRepository.save(section);
            return toSectionDTO(updateSection);
          })
          .orElseThrow(()->  new ResourceNotFound("Section not exist by idSection : " + id));

    }


    /**
     * Delete section by id
     * @param id
     */
    public void delete(Long id) {
        Section section = this.sectionRepository.findById(id)
        .orElseThrow(() ->  new ResourceNotFound("Section Not Found by id : " + id));

        section.setEnabled(Boolean.FALSE);
        this.sectionRepository.save(section);
    }

    //@Cacheable(GET_SECTION_BY_ID)
    public SectionDTO getById(Long id){
        Section section  = this.sectionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFound("Section Not Found by Id : "+ id));

        SectionDTO sectionDTO = SectionService.toSectionDTO(section);
        return sectionDTO;
    }

    public static Section toSection(SectionDTO sectionDTO){
        Section section = new Section();
        section.setId(sectionDTO.getId());
        section.setName(sectionDTO.getName());
        section.setEnabled(sectionDTO.getEnabled());
        section.setDescription(sectionDTO.getDescription());
        
        return section;
    }

    public static SectionDTO toSectionDTO(Section section){
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(section.getId());
        sectionDTO.setName(section.getName());
        sectionDTO.setDescription(section.getDescription());
        sectionDTO.setEnabled(section.getEnabled());
        return sectionDTO;
    }


}
