package com.tramite.online.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.tramite.online.domain.entity.FormUser;
import com.tramite.online.domain.models.FormUserDTO;
import com.tramite.online.domain.models.PagedResult;
import com.tramite.online.repository.FormRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class FormService {

    private final FormRepository formRepository;



    public PagedResult<FormUserDTO> findAll(int page, int size, 
    String sortDirection,FormUserDTO formDTO ){
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        final Sort sort = Sort.by(direction, "name");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<FormUserDTO> pageForm =  this.formRepository.findAll(pageable)
        .ma()

        return new PagedResult<>(
            pageForm.getContent, 
            pageForm.g, page, page, false, false, false, false)
    } 


    private static FormUserDTO toFormDTO(FormUser entity){
        FormUserDTO formUserDTO = new FormUserDTO();
        formUserDTO.setId(entity.getId());
        formUserDTO.setName(entity.getName());
        formUserDTO.setDescription(entity.getDescription());
        
        return formUserDTO;
    }
    
}
