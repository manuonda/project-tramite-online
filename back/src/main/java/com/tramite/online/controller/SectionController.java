package com.tramite.online.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tramite.online.domain.dto.SectionDTO;
import com.tramite.online.service.SectionService;


@RestController
@RequestMapping("/api/v1/section")
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
    public ResponseEntity<SectionDTO> save(@Validation SectionDTO sectionDTO){
        logger.info("Save Section : {}", sectionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(this.sectionService.save(sectionDTO));
    }


    

}
