package com.tramite.online.controller;

import static com.tramite.online.constants.GeneralConstants.ASC;
import static com.tramite.online.constants.GeneralConstants.DEFAULT_PAGE_NUMBER;
import static com.tramite.online.constants.GeneralConstants.DEFAULT_PAGE_SIZE;
import static com.tramite.online.constants.GeneralConstants.ID_IN_PATH;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tramite.online.domain.models.FormUserDTO;
import com.tramite.online.domain.models.PagedResult;
import com.tramite.online.service.FormUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//https://blog.jetbrains.com/idea/2025/02/database-migrations-in-the-real-world/

@RestController
@RequestMapping("/api/v1/forms")
@Tag(name = "Rest Controller Form", description = "Rest Controllers for forms")
@Slf4j
public class FormUserController {

    private final FormUserService formUserService;

    public FormUserController(FormUserService formUserService) {
        this.formUserService = formUserService;
    }

    @GetMapping
    @Operation(summary = "Get all forms", description = "Get all forms with pagination")
    @ApiResponse(responseCode = "200", description = "Return all forms")
    public ResponseEntity<PagedResult<FormUserDTO>> getAll(@RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(defaultValue = ASC) String sorted) {
        log.info("Get all pagination {},{}", page, size);
        PagedResult<FormUserDTO> pagedResult = this.formUserService.findAll(page, size, sorted, null);
        return ResponseEntity.status(HttpStatus.OK).body(pagedResult);
    }

    @PostMapping
    @Operation(
        summary ="Create a new form user",
        description = "Create a new form user"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Create a new form user"
    )
    public ResponseEntity<FormUserDTO>  createFormUser(@Valid @RequestBody FormUserDTO formUserDTO){ 
        log.info("Create form user dto {}", formUserDTO);
        FormUserDTO saveUserDTO = this.formUserService.save(formUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUserDTO);
    }

    @Operation(summary = "Get Form By Id", description = "Get Form By Id")
    @ApiResponse(responseCode = "200", description = "Get Form By Id")
    @GetMapping(value = ID_IN_PATH, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormUserDTO> getFormById(@PathVariable("id") Long id) {
        log.info("Get by Id Form :{}", id);
        FormUserDTO formUserDTO = this.formUserService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(formUserDTO);
    }

    @Operation(summary ="Update Form",
    description = "Update Form by Id")
    @ApiResponse(responseCode = "200", description="Update Form by Id")
    @PutMapping(value = ID_IN_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormUserDTO> updateFormById(@PathVariable("id") Long id,
    @RequestBody FormUserDTO dto){
       log.info("Update User by Id : {} {}", id, dto);
        FormUserDTO formUserDTO =  this.formUserService.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(formUserDTO);
    }

    @Operation(summary = "Delete by Id" , description = "Delete by id Form User")
    @ApiResponse(responseCode = "200", description="Response None ")
    @DeleteMapping(ID_IN_PATH)
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        return null;
    }





}
