package com.tramite.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tramite.online.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
