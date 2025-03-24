package com.tramite.online.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tramite.online.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findByProviderAndProviderId(String provider, String idProvider);

    Optional<User> findByEmailAndProvider(String email, String provider);

    boolean existsByEmail(String email);
}
