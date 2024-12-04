package com.tramite.online.service;

import java.util.List;
import java.util.Optional;

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

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SectionService {

    
    private final SectionRepository sectionRepository;
    private final QuestionRepository questionRepository;


    public PagedResult<SectionDTO> findAll(int page, int size, String sortDirection,SectionDTO sectionDTO){
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        final Sort sort = Sort.by(direction, "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<SectionDTO> pageSections = this.sectionRepository.findAll(pageable)
        .map(SectionService::toSectionDTO);
     

        // List<Section> sections = this.sectionRepository.findAll();
        // return sections.stream()
        //        .map(SectionService::toSectionDTO)
        //        .toList();

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
        
        Section section = this.toSection(sectionDTO);
        Optional<Section> sectionFindByName = this.sectionRepository.findByName(section.getName());
        if ( sectionFindByName.isPresent()){
            throw new ResourceFound("Section name already exist");
        }

        return this.toSectionDTO(this.sectionRepository.save(section));
    }



    /**
     * Method Section <strong> update</strong>
     * @param id
     * @param sectionDTO
     * @return
     */
    public SectionDTO update(Long id, SectionDTO sectionDTO){

          this.sectionRepository.findById(id)
          .orElseThrow(()->  new ResourceNotFound("Section not exist by idSection : " + id));

         Optional<Section> findByName = this.sectionRepository.findByName(sectionDTO.getName());
         if ( findByName.isPresent() && !findByName.get().getId().equals(sectionDTO.getId())){
            throw new ResourceFound("Section name exist in other Section");
         }

         Section section = this.sectionRepository.save(SectionService.toSection(sectionDTO));
         return SectionService.toSectionDTO(section);
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

    public SectionDTO getById(Long id){
        Section section  = this.sectionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFound("Section Not Found by Id : "+ id));

        return SectionService.toSectionDTO(section);
    }

    private static Section toSection(SectionDTO sectionDTO){
        Section section = new Section();
        section.setId(sectionDTO.getId());
        section.setName(sectionDTO.getName());
        section.setEnabled(sectionDTO.getEnabled());
        section.setDescription(sectionDTO.getDescription());
        
        return section;
    }

    private static SectionDTO toSectionDTO(Section section){
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(section.getId());
        sectionDTO.setName(section.getName());
        sectionDTO.setDescription(section.getDescription());
        sectionDTO.setEnabled(section.getEnabled());
        return sectionDTO;
    }


}
