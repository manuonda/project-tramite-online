package com.tramite.online.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tramite.online.domain.dto.SectionDTO;
import com.tramite.online.service.SectionService;

import io.micrometer.core.ipc.http.HttpSender.Response;
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
    public List<SectionDTO> getAll() {
        return null;
    }

    @PostMapping
    @Operation(summary = "Save SectionDTO", description = "Create Section DTO")
    @ApiResponse( responseCode = "200", description = "Response Code 200")
    public ResponseEntity<SectionDTO> save(@Valid @RequestBody SectionDTO sectionDTO){
        logger.info("Save Section : {}", sectionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(this.sectionService.save(sectionDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable("id") Long id){
        logger.info("Delete Section by Id : {}", id);
        this.sectionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Section ");
    }


    

}
