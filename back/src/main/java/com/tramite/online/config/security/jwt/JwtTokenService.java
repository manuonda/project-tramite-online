package com.tramite.online.config.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tramite.online.config.ApplicationProperties;
import com.tramite.online.config.security.oauth2.factory.OAuth2UserInfoExtractorFactory;
import com.tramite.online.service.UserManagementService;


@Service
public class JwtTokenService {


    private final ApplicationProperties applicationProperties;
    private final UserManagementService userManagementService;
    private final OAuth2UserInfoExtractorFactory auth2UserInfoExtractorFactory;


    
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenService.class);
   
    public JwtTokenService( ApplicationProperties applicationProperties,
      UserManagementService userManagementService,
      OAuth2UserInfoExtractorFactory auth2UserInfoExtractorFactory
    ) {
        this.applicationProperties = applicationProperties;
        this.userManagementService = userManagementService;
        this.auth2UserInfoExtractorFactory = auth2UserInfoExtractorFactory;
    }

    



}
