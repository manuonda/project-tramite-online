package com.tramite.online.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tramite.online.domain.entity.FormUser;

public interface FormUserRepository extends JpaRepository<FormUser, Long>{

    Optional<FormUser> findByName(String name);
    
}
