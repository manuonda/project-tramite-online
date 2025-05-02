package com.tramite.online.service.validator;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.tramite.online.domain.entity.FormUser;
import com.tramite.online.domain.models.FormUserDTO;
import com.tramite.online.exception.ResourceFound;
import com.tramite.online.repository.FormUserRepository;


@Component
public class ValidatorFormUser {

    private static final Logger logger= org.slf4j.LoggerFactory.getLogger(ValidatorFormUser.class);
     
    private final  FormUserRepository formUserRepository;

    public ValidatorFormUser(FormUserRepository formUserRepository){
        this.formUserRepository = formUserRepository;
    }
    public void validate(FormUserDTO dto){
        logger.info("Validate Form User DTO");
        Optional<FormUser> findFormByName  = this.formUserRepository.findByName(dto.getName());
        if(findFormByName.isPresent() && !findFormByName.get().getId().equals(dto.getId())){
            throw new ResourceFound("Field Name exists in other Form User");
        }
        
    } 
    
}
