package com.tramite.online.controller;

import static com.tramite.online.constants.GeneralConstants.ASC;
import static com.tramite.online.constants.GeneralConstants.DEFAULT_PAGE_NUMBER;
import static com.tramite.online.constants.GeneralConstants.DEFAULT_PAGE_SIZE;
import static com.tramite.online.constants.GeneralConstants.ID_IN_PATH;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.tramite.online.domain.models.PagedResult;
import com.tramite.online.domain.models.SectionDTO;
import com.tramite.online.service.SectionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/section")
@Tag(name="Section Controller", description = "Section Controller Contains operations CRUD and others operations.")
public class SectionController {


    private static final Logger logger = LoggerFactory.getLogger(SectionController.class);

    private final SectionService sectionService;

    public SectionController(SectionService sectionService){
        this.sectionService = sectionService;
    }


    @GetMapping
    @Operation(summary = "Get Pagination Section" ,
    description = "")
    public ResponseEntity<PagedResult<SectionDTO>> getAll(
        @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size,
        @RequestParam(defaultValue = ASC) String sorted) {
         logger.info("Get All pagination {},{}", page, size);
         PagedResult<SectionDTO> rPagedResult = this.sectionService.findAll(page, size, sorted, null);

         return ResponseEntity.status(HttpStatus.OK).body(rPagedResult);
    }

    @PostMapping
    @Operation(summary = "Save SectionDTO", description = "Create Section DTO")
    @ApiResponse( responseCode = "200", description = "Response Code 200")
    public ResponseEntity<SectionDTO> save(@Valid @RequestBody SectionDTO sectionDTO){
        logger.info("Save Section : {}", sectionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(this.sectionService.save(sectionDTO));
    }


    @Operation(summary="Delete Section by Id", description = "Delete Section by Id")
    @ApiResponse(responseCode = "200", description = "Response Code 200")
    @DeleteMapping(ID_IN_PATH)
    public ResponseEntity<String> deleteSection(@PathVariable("id") Long id){
        logger.info("Delete Section by Id : {}", id);
        this.sectionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Section ");
    }



    @ApiResponse(responseCode = "200", description = "Response Code 200")
    @Operation(summary = "Update Section by Id", description = "Update Section by Id parameter")
    @PutMapping(ID_IN_PATH)
    public ResponseEntity<SectionDTO> updateSection(@PathVariable("id") Long id, 
    @RequestBody SectionDTO sectionDTO){
        logger.info("Update Section By Id : {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(this.sectionService.update(id, sectionDTO));
    }


    @Operation(summary = "Get Section by Id", description = "Get Section by Id")
    @ApiResponse(responseCode = "200", description = "Response Code 200")
    @GetMapping(value = ID_IN_PATH , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SectionDTO> getSectionById(@PathVariable("id")Long id) {
        logger.info("Get Section by Id : {}" ,id);
        SectionDTO sectionDTO = this.sectionService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(sectionDTO);
    }


}
