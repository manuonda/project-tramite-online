package com.tramite.online.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tramite.online.repository.QuestionRepository;
import com.tramite.online.repository.SectionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final QuestionRepository questionRepository;


    public List<SectionDTO> findAll(){

    }
}
