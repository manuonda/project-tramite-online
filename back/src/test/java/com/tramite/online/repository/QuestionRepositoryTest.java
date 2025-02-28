package com.tramite.online.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.tramite.online.TestContainerConfiguration;
import com.tramite.online.domain.entity.Question;


@DataJpaTest
@Import(TestContainerConfiguration.class)
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private SectionRepository sectionRepository;

    

    Question question;

    @BeforeEach
    void setUp(){
        
    }

    


}
