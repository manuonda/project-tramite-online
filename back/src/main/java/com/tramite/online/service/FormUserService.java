package com.tramite.online.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.tramite.online.domain.entity.FormUser;
import com.tramite.online.domain.entity.Section;
import com.tramite.online.domain.models.FormUserDTO;
import com.tramite.online.domain.models.PagedResult;
import com.tramite.online.exception.ResourceFound;
import com.tramite.online.exception.ResourceNotFound;
import com.tramite.online.repository.FormUserRepository;
import com.tramite.online.service.validator.ValidatorFormUser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class FormUserService {

    private final FormUserRepository formUserRepository;
    private final ValidatorFormUser validatorFormUser;
    private final SectionService sectionService;


    public PagedResult<FormUserDTO> findAll(int page, int size, 
    String sortDirection,FormUserDTO formDTO ){
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        final Sort sort = Sort.by(direction, "name");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<FormUserDTO> pageForm =  this.formUserRepository.findAll(pageable)
        .map(FormUserService::toFormDTO);

        return new PagedResult<>(
            pageForm.getContent(),
            pageForm.getTotalElements(),
            pageForm.getNumber(),
            pageForm.getTotalPages(),
            pageForm.isFirst(),
            pageForm.isLast(),
            pageForm.hasNext(),
            pageForm.hasPrevious()
            );
    } 

    @Transactional
    public FormUserDTO save(FormUserDTO formDTO){
        FormUser formUser = FormUserService.toEntity(formDTO);
        Optional<FormUser> formUserOptional = this.formUserRepository.findByName(formDTO.getName());
        if (formUserOptional.isPresent()){
            throw new ResourceFound("Form User already exists");
        }
        formUser = toEntity(formDTO);
        formUser = this.formUserRepository.save(formUser);
        return toFormDTO(formUser);
    }


    @Transactional
    public FormUserDTO update(FormUserDTO dto, Long id){
        this.validatorFormUser.validate(dto);

        return this.formUserRepository.findById(id)
        .map(existFormUser  -> {
            FormUser formUser = FormUserService.toEntity(dto);
            formUser.setId(id);
            FormUser update = this.formUserRepository.save(formUser);
            return toFormDTO(update);
        })
        .orElseThrow(()-> new ResourceNotFound("Form User Not Exist by Id : " +  id));
    }

    
    public FormUserDTO getById(Long id){
        log.info("Find User by Id {}" , id);
        return this.formUserRepository.findById(id)
        .map( FormUserService::toFormDTO)
        .orElseThrow(() ->  new ResourceNotFound("Form User Not found by id " + id ));
    }

    public  static FormUserDTO toFormDTO(FormUser entity){
        FormUserDTO formUserDTO = new FormUserDTO();
        formUserDTO.setId(entity.getId());
        formUserDTO.setName(entity.getName());
        formUserDTO.setDescription(entity.getDescription());
        
        return formUserDTO;
    }

    public static FormUser toEntity(FormUserDTO dto){
        FormUser formUser = new FormUser();
        formUser.setId(dto.getId());
        formUser.setName(dto.getName());
        formUser.setDescription(dto.getDescription());
        Set<Section> sections = new HashSet<>();
        dto.getSections().forEach(sectionDTO -> {
            log.debug("Adding SectionDTO ", sectionDTO.getId());
            Section section = SectionService.toSection(sectionDTO);
            section.setForm(formUser);
            sections.add(section);
        });
        formUser.setSections(sections);
        return formUser;
    }
    
}
