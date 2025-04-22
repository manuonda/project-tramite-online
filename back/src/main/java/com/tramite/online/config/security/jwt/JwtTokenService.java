package com.tramite.online.config.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tramite.online.config.ApplicationProperties;


@Service
public class JwtTokenService {


    private final ApplicationProperties applicationProperties;
    
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenService.class);
   
    public JwtTokenService( ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }


}
