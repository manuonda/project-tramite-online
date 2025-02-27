package com.tramite.online.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.tramite.online.TestContainerConfiguration;


@DataJpaTest
@Import(TestContainerConfiguration.class)
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private SectionRepository sectionRepository;

    

}
