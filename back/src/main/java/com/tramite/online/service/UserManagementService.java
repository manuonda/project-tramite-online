
package com.tramite.online.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.domain.entity.User;
import com.tramite.online.repository.UserRepository;
import  org.slf4j.LoggerFactory;


/**
 * Service for managing users, focusing on saving or updating 
 * user data from OAuth2 providers.
 */

@Service
public class UserManagementService {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserManagementService.class);

    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Function that save userInfo from 
     * OAuth2 from provideers
     * @param userInfo
     */
    public void processOAuthUser(UserInfo userInfo){
        logger.info("Saving user from OAuth2 : {}",userInfo);

        Optional<User> existingUser = this.userRepository.findByProviderAndProviderId(userInfo.getProvider(), 
        userInfo.getProviderId());
        
        if (existingUser.isEmpty()){
            existingUser = this.userRepository.findByEmail(userInfo.getEmail());
        }

        User user;
        if (existingUser.isPresent()){
            logger.info("User find {}",existingUser.get());
            user = existingUser.get();
            user.setEmail(userInfo.getEmail());
            user.setUserName(userInfo.getName());

        } else {
            logger.info("User not existing by Provider {} and IdProvider {}", userInfo.getProviderId() , userInfo.getProvider().getValue());
            user = this.mapUserInfoToUser(userInfo);
        }
        
        this.userRepository.save(user);
    }


    private User mapUserInfoToUser(UserInfo userInfo){

        User user = new User();
        user.setUserName(userInfo.getName());
        user.setEmail(userInfo.getEmail());
        user.setProvider(userInfo.getProvider());
        user.setProviderId(userInfo.getProviderId());
        return user;
    }

}
