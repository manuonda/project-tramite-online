package com.tramite.online.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "application.oauth2")
@Component
public record OAuth2Properties(
List<String> authorizedRedirectUris
) {
       
    public OAuth2Properties {
        if ( authorizedRedirectUris == null ) {
            authorizedRedirectUris = new ArrayList<>();
        }
    }
}
