package com.tramite.online.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tramite.online.config.security.model.TypeProvider;
import com.tramite.online.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findByProviderAndProviderId(TypeProvider provider, String idProvider);

    Optional<User> findByEmailAndProvider(String email, String provider);

    boolean existsByEmail(String email);

    Optional<User> findByUserName(String username);
}
