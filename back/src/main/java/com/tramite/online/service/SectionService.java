package com.tramite.online.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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


    public List<SectionDTO> findAll(){
        List<Section> sections = this.sectionRepository.findAll();
        return sections.stream()
               .map(SectionService::toSectionDTO)
               .toList();
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

         Section section = this.sectionRepository.save(this.toSection(sectionDTO));
         return this.toSectionDTO(section);
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


    public void delete(Long id) {
        Section section = this.sectionRepository.findById(id)
        .orElseThrow(() ->  new ResourceNotFound("Section "));

        section.setEnabled(Boolean.FALSE);
        this.sectionRepository.save(section);
    }
}
