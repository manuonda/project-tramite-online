package com.tramite.online.service.validator;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.tramite.online.domain.entity.FormUser;
import com.tramite.online.domain.models.FormUserDTO;
import com.tramite.online.exception.ResourceFound;
import com.tramite.online.repository.FormUserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class ValidatorFormUser {

    private static final Logger logger= org.slf4j.LoggerFactory.getLogger(ValidatorFormUser.class);
     
    private FormUserRepository repository;

    public void validate(FormUserDTO dto){
        logger.info("Validate Form User DTO");
        Optional<FormUser> findFormByName  = this.repository.findByName(dto.getName());
        if(findFormByName.isPresent() && !findFormByName.get().getId().equals(dto.getId())){
            throw new ResourceFound("Field Name exists in other Form User");
        }
        
    } 

    
}
