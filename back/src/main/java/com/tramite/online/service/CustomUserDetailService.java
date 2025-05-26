/**
 * Custom service for user authentication.
 * Implements UserDetailsService to load user details by username or ID.
 */
package com.tramite.online.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tramite.online.config.security.principal.UserPrincipal;
import com.tramite.online.domain.entity.User;
import com.tramite.online.exception.ResourceNotFound;
import com.tramite.online.repository.UserRepository;




@Service
public class CustomUserDetailService implements  UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        User user = this.userRepository.findByUserName(username).
        orElseThrow( () ->  new UsernameNotFoundException("UserName not found with username : " + username));


        return UserPrincipal.create(user);

    }

    public UserDetails loadUserById(Long id){
        User user = this.userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFound("User no encontrado by id : " + id));
        
        return UserPrincipal.create(user);
    }



}
