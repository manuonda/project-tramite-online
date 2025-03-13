/**
 * CustomOAuth2UserService is a service class that extends the DefaultOAuth2UserService
 * to customize the process of extracting user information from OAuth2 providers 
 * such as Google, Facebook, and others.
 *
 * This class overrides the loadUser method to handle the retrieval of user details
 * from the OAuth2UserRequest and allows for additional processing or integration
 * with the application's user repository.
 *
 * Dependencies:
 * - UserRepository: Used to interact with the application's user data.
 *
 * Annotations:
 * - @Service: Marks this class as a Spring service component.
 * - @RequiredArgsConstructor: Generates a constructor with required arguments 
 *   (final fields) for dependency injection.
 */
package com.tramite.online.config.security.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tramite.online.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        return super.loadUser(userRequest);
    }

    
}
