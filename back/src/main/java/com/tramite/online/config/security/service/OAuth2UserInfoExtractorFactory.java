package com.tramite.online.config.security.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tramite.online.config.security.model.TypeProvider;
import com.tramite.online.config.security.service.extractor.FacebookOAuth2UserInfoExtractor;
import com.tramite.online.config.security.service.extractor.GitHubOAuth2UserInfoExtractor;
import com.tramite.online.config.security.service.extractor.GoogleOAuth2UserInfoExtractor;

import lombok.extern.slf4j.Slf4j;


/**
 * OAuth2UserInfoExtractorFactory es una clase que actúa como una fábrica para gestionar
 * y proporcionar estrategias de extracción de información de usuarios autenticados
 * mediante OAuth2. 
 *
 * Esta clase utiliza inyección de dependencias para recibir implementaciones específicas
 * de estrategias de extracción (Google, GitHub, Facebook) y las organiza en un mapa inmutable.
 *
 * Métodos principales:
 * - getExtractor(String registrationId): Devuelve la estrategia de extracción asociada
 *   al proveedor de OAuth2 especificado por el registrationId.
 * - getAllExtractors(): Devuelve todas las estrategias de extracción disponibles.
 *
 * Uso de inyección de dependencias:
 * Las estrategias específicas (GoogleOAuth2UserInfoExtractor, GitHubOAuth2UserInfoExtractor,
 * FacebookOAuth2UserInfoExtractor) se pasan al constructor mediante inyección de dependencias,
 * facilitando la extensibilidad y el desacoplamiento.
 */
@Component
@Slf4j
public class OAuth2UserInfoExtractorFactory {
   private final Map<String, OAuth2UserInfoExtractorStrategy> extractors;
  
   public OAuth2UserInfoExtractorFactory(
    GoogleOAuth2UserInfoExtractor googleExtractor,
    GitHubOAuth2UserInfoExtractor gitHubExtractor,
    FacebookOAuth2UserInfoExtractor facebookExtractor
   ){
    
    Map<String, OAuth2UserInfoExtractorStrategy> extractorsMap = new HashMap<>();
    extractorsMap.put(TypeProvider.FACEBOOK.getValue(), facebookExtractor);
    extractorsMap.put(TypeProvider.GOOGLE.getValue(), googleExtractor);
    extractorsMap.put(TypeProvider.GITHUB.getValue(), gitHubExtractor);
    
    this.extractors = Collections.unmodifiableMap(extractorsMap);
    log.info(null);

   }

   public OAuth2UserInfoExtractorStrategy getExtractor(String registrationId){
    return extractors.get(registrationId);
   }

   public Map<String, OAuth2UserInfoExtractorStrategy>  getAllExtractors(){
    return extractors;
   }

}
