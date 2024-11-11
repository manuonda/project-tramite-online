package com.tramite.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.tramite.online.domain.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>, QueryByExampleExecutor<Question>{

}
