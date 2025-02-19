package com.tramite.online.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tramite.online.domain.models.FormUserDTO;
import com.tramite.online.domain.models.PagedResult;
import com.tramite.online.service.FormUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import static com.tramite.online.constants.GeneralConstants.ASC;
import static com.tramite.online.constants.GeneralConstants.DEFAULT_PAGE_NUMBER;
import static com.tramite.online.constants.GeneralConstants.DEFAULT_PAGE_SIZE;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/forms")
@Tag(
    name="Rest Controller Form",
    description ="Rest Controllers for forms"
)
@Slf4j
public class FormUserController {

    private final FormUserService formUserService;


    public FormUserController( FormUserService formUserService){
        this.formUserService = formUserService;
    }



    @GetMapping
    public ResponseEntity<PagedResult<FormUserDTO>> getAll(
      @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size,
      @RequestParam(defaultValue = ASC) String sorted
    ){
        log.info("Get all pagination {},{}", page, size);
        PagedResult<FormUserDTO> pagedResult = this.formUserService.findAll(page, size, sorted, null);
        return ResponseEntity.status(HttpStatus.OK).body(pagedResult);
    }


    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PutMapping(ID_IN_PATH)
    
    
}
