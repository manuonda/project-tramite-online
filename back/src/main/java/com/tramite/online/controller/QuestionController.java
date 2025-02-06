package com.tramite.online.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/questions")
@Tag(name = "Question Controller", description = "Question Controller Contains Operations")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
}
