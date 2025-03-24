
package com.tramite.online.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.domain.entity.User;
import com.tramite.online.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Service class responsible for managing user-related operations, 
 * particularly handling user information obtained from OAuth2 providers.
 * This includes saving or updating user details in the database 
 * based on the pr

 */

@Service
@AllArgsConstructor
@Slf4j
public class UserManagementService {

    private final UserRepository userRepository;

    /**
     * Function that save userInfo from 
     * OAuth2 from provideers
     * @param userInfo
     */
    public void processOAuthUser(UserInfo userInfo){
        log.info("Saving user from OAuth2 : {}",userInfo);

        Optional<User> existingUser = this.userRepository.findByProviderAndProviderId(userInfo.getProvider(), 
        userInfo.getProviderId());
        
        if (existingUser.isEmpty()){
            existingUser = this.userRepository.findByEmail(userInfo.getEmail());
        }

        User user;
        if (existingUser.isPresent()){
            log.info("User find {}",existingUser.get());
            user = existingUser.get();
            user.setEmail(userInfo.getEmail());
            user.setUserName(userInfo.getName());

        } else {
            log.info("User not existing by Provider {} and IdProvider {}", userInfo.getProviderId() , userInfo.getProvider().getValue());
            user = this.mapUserInfoToUser(userInfo);
        }
         this.userRepository.save(user);
    }


    private User mapUserInfoToUser(UserInfo userInfo){

        return User.builder()
        .userName(userInfo.getName())
        .email(userInfo.getEmail())
        .provider(userInfo.getProvider())
        .providerId(userInfo.getProviderId())
        .build();
    }

}
